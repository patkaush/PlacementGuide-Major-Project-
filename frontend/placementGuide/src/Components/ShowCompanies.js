import { useState } from "react";
import useFetch from "./useFetch";
import { Link } from 'react-router-dom';
import {
    Box,
    Center,
    Heading,
    Text,
    Stack,
    Avatar,
    useColorModeValue,
    VStack,
    HStack,
    SimpleGrid,
    Input,
  } from '@chakra-ui/react';
  
const ShowCompanies = () => {
  
    let [subHeading,setSubHeading] = useState("Display Companies");
    localStorage.setItem("header",subHeading);
    const [allCompanies,setAllCompanies] = useFetch({
        endpoint : "/company/companiesLimit?count=50",
        resource : subHeading
    });
    let [searchBar,setSearchBar] = useState("");
    let  handleSearch = (e) =>{
      // e.preventDefault();
      setSearchBar(e.target.value);
      if(e.target.value == ""){
        console.log(searchBar);
        console.log(allCompanies);
        setAllCompanies(allCompanies);  
      }else{
        setAllCompanies(allCompanies.filter(company => {
          return company.name.toLowerCase().includes(searchBar);
        }))
  
      }
    }
    
    return (  
        <>
        
        {/* <Center><Heading style={{align:'center'}}>{subHeading}</Heading></Center> */}
        <Input type='text'w='500px'  borderColor='teal' marginLeft='500px' placeholder="Search For Company" onChange={handleSearch}/>

    <SimpleGrid  minChildWidth='400px' spacing='40px' >
    {allCompanies &&allCompanies.map((company) => (
      <Link key={company.id} to={`/company/${company.id}`}>
      <Box
        maxW={'445px'}
        w={'full'}
        bg={'white'}
        boxShadow={'2xl'}
        rounded={'md'}
        p={6}
        overflow={'hidden'}>
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
      </Box>
      </Link>
      ))}
      </SimpleGrid >
      </>
    );
}
 
export default ShowCompanies;

/*
<div>
{subHeading}
<ol>
    { tempTable}
</ol>
</div>
fore ref
    let tempTable = [];
    if(allCompanies){
        // alert(allCompanies.data)
        for(let key of allCompanies){
            const row = (
                <li>
                    <Link to={`/company/${key.id}`}>{key.companyName}</Link>
                </li>
            );
            tempTable.push(row);
        }

 */