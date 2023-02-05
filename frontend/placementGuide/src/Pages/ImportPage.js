import { Alert, Box, Button, Flex, Heading, Input, Select, Stack, Table, TableContainer, Tbody, Td, Text, Th, Thead, Tr } from "@chakra-ui/react";
import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router";
import Navbar from "./Navbar";



const ImportPage = () => {
    const [array, setArray] = useState([]);
    const [selected,setOption] = useState('0');

    const [file, setFile] = useState();

    const fileReader = new FileReader();

    const handleOnChange = (e) => {
        setFile(e.target.files[0]);

    };
    const csvFileToArray = string => {
        const csvHeader = string.slice(0, string.indexOf("\n")).split(";");
        const csvRows = string.slice(string.indexOf("\n") + 1).split("\n");
    
        const array = csvRows.map(i => {
          const values = i.split(";");
          const obj = csvHeader.reduce((object, header, index) => {
            object[header] = values[index];
            return object;
          }, {});
          return obj;
        });
    
        setArray(array);
      };
      const headerKeys = Object.keys(Object.assign({}, ...array));
    const handleOnSubmit = (e) => {
        e.preventDefault();

        if (file) {
            fileReader.onload = function (event) {
                const text = event.target.result;
                csvFileToArray(text);

            };

            fileReader.readAsText(file) ;
        }
    };
    let nav = useNavigate();
    let submitUsers = ()=>{
        let users = [];
        console.log(array)
        console.log(headerKeys)

        for(let row of array){
            let temp = {UserId: localStorage.getItem("id"),Username:localStorage.getItem("username")};
            let i = 0;
            for(let col of Object.values(row) ){
                if(typeof(col)==='undefined')continue;
                temp[headerKeys[i].toLowerCase().trim()] = col.toLowerCase().trim();
                i+=1;
            }
            if(temp['username']){
                users.push(temp);
            }
        }
        let reqData = {users}
        let confHeades = {
            headers:{
                "Authorization":"Bearer "+localStorage.getItem("token"),
                "id":localStorage.getItem("id"),
            }
        }        
        axios
        .post(localStorage.getItem("url")+"/user/addUsers",reqData,confHeades)
        .then(res =>{
            nav("/");
            console.log(res.data);
        })
        .catch(err => {
            if(err.response){
                console.log(err.response.data);
            }
        })   
        console.log(reqData);

    }
    let submitCompanies=()=>{

        let companies = [];
        for(let row of array){
            let temp = {UserId: localStorage.getItem("id"),Username:localStorage.getItem("username")};
            let i = 0;
            for(let col of Object.values(row) ){
                if(headerKeys[i]=="comments"){  
                    let arr={}

                    arr['cid']=0;
                    
                    arr['data']=col;

                    arr['date']='0';
                    arr['username']=localStorage.getItem('username');
                    temp[headerKeys[i]] = [arr];

                }else if(headerKeys[i]=="resources"){
                    let arr={}
                    arr['rid']=0;
                    arr['resourceData']=col
                    arr['date']='0';
                    temp[headerKeys[i]] = [arr];

                }
                
                else{

                    temp[headerKeys[i].toLowerCase()] = col;
                }
                i+=1;
            }
            if(temp['name']){
                companies.push(temp);
            }
        }
        let reqData = {companies}
        let confHeades = {
            headers:{
                "Authorization":"Bearer "+localStorage.getItem("token"),
                "id":localStorage.getItem("id"),
            }
        }           
        axios
        .post(localStorage.getItem("url")+"/company/addCompanies",reqData,confHeades)
        .then(res =>{
            nav("/");
            console.log(res.data);
        })
        .catch(err => {
            if(err.response){
                console.log(err.response.data);
            }
        })
    }
    function submitData(selected){
        switch(selected){
            case '1':submitUsers();return;
            case '2':submitCompanies();return;
        }
        alert("Select An Option");
    
    }
    
    return (  
        <>
        <Navbar/>

        <Heading marginLeft={'500px'} paddingBottom='30px'>Perform Bulk Upload</Heading>
        <Select onClick={(e) => setOption(e.target.value)} placeholder='Select Type Of Data' w='500px' paddingLeft='300px' marginLeft='350px' borderColor='cyan.900'>
            <option value='1' >Users Data</option>
            <option value='2' >Company Data</option>
        </Select>
        <div style={{ textAlign: "center",paddingTop:'40px' }}>
            <form>
            <input
                    type={"file"}
                    id={"csvFileInput"}
                    accept={".csv"}
                    onChange={handleOnChange}
            />
                 <Button onClick={(e) => {
                        handleOnSubmit(e);}}>IMPORT CSV</Button>
            </form>
        </div>
        <br />

            <TableContainer >
                <Table variant='striped' colorScheme='teal'  >
            <Thead>
                <Tr key={"header"}>
                    {headerKeys.map((key) => (
                        <Th>{key}</Th>
                        
                    ))}
                </Tr>
            </Thead>

            <Tbody>
            {array.map((item) => (
                    <Tr key={item.id}  >
                        {Object.values(item).map((val) => (
                                                        
                        <Td > <Text  isTruncated>{val} </Text></Td>
                        ))}
                    </Tr>
                    ))}

            </Tbody>

             </Table>

            </TableContainer>
            <Button colorScheme='blue'm='50px' marginLeft='700px' onClick={()=>submitData(selected)}>Submit</Button>

        </>
    );
}
 
export default ImportPage;