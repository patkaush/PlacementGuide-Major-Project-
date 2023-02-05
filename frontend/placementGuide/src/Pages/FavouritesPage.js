import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import ShowCompanies from "../Components/ShowCompanies";
import useFetch from "../Components/useFetch";
import Navbar from "./Navbar";
import {
    Box,
    Center,
    Heading,
    Text,
    Stack,
    SimpleGrid,
  } from '@chakra-ui/react';
import { AlertDialog, AlertDialogBody, AlertDialogContent, AlertDialogFooter, AlertDialogHeader, AlertDialogOverlay, Button, useDisclosure } from "@chakra-ui/react";
import { useRef } from "react";

const FavourtiesPage = () => {
    let [subHeading,setSubHeading] = useState("Favourite Companies");
    localStorage.setItem("header",subHeading);

    let [allCompanies,setAllCompanies] =  useFetch({
        endpoint : "/user/wishList",
        resource : subHeading
    });
    
    let deleteFavourite = (id) => {
        onClose();
        let confHeaders = {
            headers:{
                "Authorization":"Bearer "+localStorage.getItem("token"),
                "id":localStorage.getItem("id"),
                "companyid":id
            }
        }
        const devUrl = "http://localhost:8081/placementGuide/user/delCompany";
    const devployUrl = "https://noob0me.herokuapp.com/user/delCompany"

        axios
        .delete(localStorage.getItem("url")+"/user/delCompany",confHeaders)
        .catch(err => console.log(err));
        setAllCompanies(allCompanies.filter(company => company.id != id));
                
        console.log(allCompanies);
    }
    let tempTable = [];
    if(allCompanies){
        for(let key of allCompanies){
            console.log(key)
            const row = (
                <li>
                    <div>
                    <Link to={`/company/${key.id}`}>{key.companyName}-</Link>

                    <button onClick={() => {deleteFavourite(key.id)}} style={{"margin-left":"40px"}}>Delete </button>

                    </div>

                </li>

            );
            tempTable.push(row);
        }
    
    }
    const { isOpen, onOpen, onClose } = useDisclosure()
    const cancelRef =useRef()
  
    return (  
        <>
        <Navbar/>

        <Center><Heading style={{align:'center'}}>{subHeading}</Heading></Center>

    <SimpleGrid  minChildWidth='400px' spacing='40px' >
    {allCompanies && (allCompanies.length ? allCompanies.map((company) => (
      <Box
        key={company.id}
        maxW={'445px'}
        w={'full'}
        bg={'white'}
        boxShadow={'2xl'}
        rounded={'md'}
        p={6}
        overflow={'hidden'}>
        <Link to={`/company/${company.id}`}>
        <Stack>
          <Heading
            color={'green.500'}
            fontSize={'2xl'}
            fontFamily={'body'}>
            {company.name}
          </Heading>
          <Text color={'black'}>    
                {company.description}
          </Text>
        </Stack>
        <Stack mt={6} direction={'row'} spacing={4} align={'center'}>
          <Stack direction={'column'} spacing={0} fontSize={'sm'}>
          {company.rating &&  <Text fontWeight={600}>Interview Rating - {company.rating}</Text> }
          {company.interview_difficulty &&  <Text fontWeight={600}>Interview Difficulty - {company.interview_difficulty}</Text> }
            
          </Stack>
        </Stack>
        </Link>
{/*Alert Dialog to warn user before deleting the item */}  
        <div className="alertUser">
        <Button colorScheme='red' onClick={onOpen}>
          Delete Company
        </Button>
        <AlertDialog
          isOpen={isOpen}
          leastDestructiveRef={cancelRef}
          onClose={onClose}
        >
          <AlertDialogOverlay>
            <AlertDialogContent>
              <AlertDialogHeader fontSize='lg' fontWeight='bold'>
                {company.name}
              </AlertDialogHeader>
  
              <AlertDialogBody>
                Are you sure? You can't undo this action afterwards.
              </AlertDialogBody>
  
              <AlertDialogFooter>
              <Button ref={cancelRef} onClick={onClose}>
                Cancel
              </Button>
              <Button colorScheme='red' onClick={() =>deleteFavourite(company.id)} ml={3}>
                Delete
              </Button>
  
              </AlertDialogFooter>
            </AlertDialogContent>
          </AlertDialogOverlay>
        </AlertDialog>
      </div>
{/*Alert Dialog to warn user before deleting the item */}  


      </Box>
      )) : <Heading margin='300px' marginLeft='600px' fontSize='4xl' fontWeight='3px'>Oops..Your List is Empty</Heading> 
    )}
      </SimpleGrid >
      </>


    );
}
 
export default FavourtiesPage;


/*
        <div>
            <Navbar/>
            <h1>{subHeading}</h1>
            {tempTable.length ? 
            <ol>
                { tempTable}
            </ol>
            : <h1>No records Found</h1>}
        </div>


*/