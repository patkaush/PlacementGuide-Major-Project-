import { Button, CloseButton, Heading, systemProps } from "@chakra-ui/react";
import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import useFetch from "../Components/useFetch";
import Navbar from "./Navbar";
import { Box } from "@chakra-ui/react";
import { ChakraProvider } from "@chakra-ui/react";

import { CUIAutoComplete } from "chakra-ui-autocomplete";

const InterestsPage = () => {

    let subHeading = "Choose Your Interests"
    let userIntr;
    let [pickerItems, setPickerItems] = useState(null);
    let [selectedItems, setSelectedItems] = useState([]);
    let selectedEle = new Set();
    let allintr = [];
    useEffect( () => {
        let confHeades = {
            headers:{
                "Authorization":"Bearer "+localStorage.getItem("token"),
                "id":localStorage.getItem("id"),
            }
        }
        let endpoint = "/interest/all";
        
        const abortCont =new  AbortController();
        axios
        .get(localStorage.getItem("url")+endpoint,confHeades)
        .then(res => {
            // setIntr(res.data)
            console.log(res.data)
            userIntr = new Set(res.data.userInterests);
            for(let intr of res.data.allInterests){
                allintr.push({
                    value:intr.interest,
                    label:intr.interest,
                    id:intr.id

                })
                if(userIntr.has(intr.id) && !selectedEle.has(intr.id)){
                    selectedItems.push({
                        value:intr.interest,
                        label:intr.interest,
                        id:intr.id
                    })
                    selectedEle.add(intr.id);
                }
            }
            console.log("Enter");
            console.log(allintr);
            setPickerItems(allintr)
            // allintr = [];
            setSelectedItems(selectedItems);

            console.log("Hitting the API");
        })
        .catch(err => console.log(err))
        
        return () => abortCont.abort();

    },[])
    let nav = useNavigate();
    function submitInterests(){
        let confHeades = {
            headers:{
                "Authorization":"Bearer "+localStorage.getItem("token"),
                "id":localStorage.getItem("id"),
            }
        }
        let arr = new Set();
        let valId = {};
        let j = 0;
        for(let i of selectedItems){
                arr.add(i.id);
                valId[j] = {"id":i.id , "value":i.value};
                j+=1;
        }
        const dataReq = {
            interests:Array.from(arr),
            values : valId
        }
          axios
          .post(localStorage.getItem("url")+"/interest/setUserInterests",dataReq,confHeades)
          .then(res => {
              //should redirect to home page
              localStorage.setItem("rec",true);
                nav("/");
          })
          .catch(err => console.log("fetch" + err));
    
    }

    
      const handleCreateItem = (item) => {
        setPickerItems((curr) => [...curr, item]);

        setSelectedItems((curr) => [...curr, item]);
      };
    
      const handleSelectedItemsChange = (selectedItems) => {
          
        if (selectedItems) {
          setSelectedItems(selectedItems);
        }
      };
    return ( 
        <div>
        <Navbar/>
        <Heading paddingLeft='500px'>{subHeading}</Heading>
        <Box px={8} py={4}>{
            pickerItems && selectedItems &&
            <CUIAutoComplete
            label="Enter Your Interests...."
            placeholder="Type a Skill"
            onCreateItem={handleCreateItem}
            items={pickerItems}
            
            tagStyleProps={{
              rounded: "full",
              pt: 1,
              pb: 2,
              px: 2,
              fontSize: "1rem"
            }}
            
            selectedItems={selectedItems}
            onSelectedItemsChange={(changes) =>
              handleSelectedItemsChange(changes.selectedItems)
            }
          />
          }
      </Box>
      <Button colorScheme='blue'm='50px' marginLeft='500px' onClick={submitInterests}>Submit</Button>

          </div>


     );
}
 
export default InterestsPage;

/*
        <div>
            <Navbar/>
            <Heading>{subHeading}</Heading>
            <div>
            {intr &&intr.allInterests&& intr.allInterests.map((i)=>(
            <>
            {arr.has(i.id)&&
            <Button id={i.id} rightIcon={<CloseButton size='md' onClick={() => deleteButton(i.id)}/>}  marginLeft="40px" marginTop="30px" colorScheme={arr.has(i.id)?'blue':"red"}  >{i.interest}</Button>
            }
            </>
            ))}
            </div>

            <Button colorScheme='pink'm='50px' marginLeft='500px' onClick={submitInterests}>Submit</Button>
        </div>

*/