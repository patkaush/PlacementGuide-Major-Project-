import Navbar from "./Navbar";
import { useState } from "react";
import useFetch from "../Components/useFetch";
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
  } from '@chakra-ui/react';

const ProfilesPage = () => {
    let [subHeading,setSubHeading] = useState("All Users");

    const [allProfiles,setAllProfiles] = useFetch({
        endpoint : "/user/getUsers",
        resource : subHeading
    });
    console.log(allProfiles);
    
    
    
    return (  
        <>
        <Navbar/>
        <Center><Heading style={{align:'center'}}>{subHeading}</Heading></Center>

    <SimpleGrid  minChildWidth='300px' spacing='40px' >
    {allProfiles && allProfiles.users && allProfiles.users.map((user) => (
      <Link key={user.id} to={`/user/getUserProfile/${user.id}`}>
        {console.log("User id"+user.id)};
      <Box
        maxW={'300px'}
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
            {user.username}
          </Heading>
          <Text color={'black'}>    
                {user.email}
          </Text>
        </Stack>
      </Box>
      </Link>
      ))}
      </SimpleGrid >
      </>
    );
}
 
export default ProfilesPage;