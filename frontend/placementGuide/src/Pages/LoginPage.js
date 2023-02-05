import axios from "axios";
import React, { useRef, useState } from "react";
import { useNavigate } from "react-router";
import { Link as ReactLink} from "react-router-dom";
import {
    Flex,
    Heading,
    Input,
    Button,
    InputGroup,
    Stack,
    InputLeftElement,
    chakra,
    Box,
   Link,
    Avatar,
    FormControl,
    FormLabel,
    FormErrorMessage,
    FormHelperText,
    InputRightElement,
    AlertDialog,
    AlertDialogBody,
    AlertDialogFooter,
    AlertDialogHeader,
    AlertDialogContent,
    AlertDialogOverlay,
    useDisclosure,
    Alert,
    AlertIcon,
    AlertDescription,
    CircularProgress,
  } from "@chakra-ui/react";
import { FaUserAlt, FaLock } from "react-icons/fa";
import AlertDialogBox from "../Components/AlertDialogBox";


/**
 * Logout click will be redirected here.
 * Delete the local storage data
*/

function DelToken(){
    let config = {
        headers :{
            "Authorization":"Bearer "+localStorage.getItem("token"),
            "id":localStorage.getItem("id"),
        }
    }

    axios
    .delete(localStorage.getItem("url")+"/user/logout",config)
    .then(res => {
        console.log(res.data)
        localStorage.clear();
        localStorage.setItem("url","http://localhost:8081/placementGuide");
    })
    .catch(err => {});
}

const LoginPage = () => {
    if(localStorage.getItem("token")){ 

        DelToken();
    }
    const CFaUserAlt = chakra(FaUserAlt);
    const CFaLock = chakra(FaLock);
    const [showPassword, setShowPassword] = useState(false);

    const handleShowClick = () => setShowPassword(!showPassword);

    const [username,setUsername] = useState('');
    const [password,setPassword] = useState('');
    const [loading,setLoading] = useState(false);
    const dataObj = {
        "username":username,
        "password":password
    }
        const [errMsg,setErrMsg] = useState(null);
    const navigate = useNavigate();
    const devUrl = "http://localhost:8081/placementGuide/user/login";
    const devployUrl = "https://noob0me.herokuapp.com/user/login"

   const handleSubmit = (e) =>{
        e.preventDefault();
        setLoading(true);
        
        axios
        .post(localStorage.getItem("url")+"/user/login",dataObj)
        .then(res =>{
            setLoading(false);
            navigate("/");
            localStorage.setItem("token",res.data.token);
            localStorage.setItem("id",res.data.id);
            localStorage.setItem("username",username);
            localStorage.setItem("admin",res.data.admin);

            console.log(res.data);
        })
        .catch(err => {
            setLoading(false);
            if(err.response){
                setErrMsg(err.response.data);
            }
        })
    };
    return ( 
        <>

        <Flex
        flexDirection="column"
        width="100wh"
        height="100vh"
        backgroundColor="gray.200"
        justifyContent="center"
        alignItems="center"
      >
        <Stack
          flexDir="column"
          mb="2"
          justifyContent="center"
          alignItems="center"
        >
          <Avatar bg="teal.500" />
          <Heading color="teal.400">Welcome</Heading>
          <Box minW={{ base: "90%", md: "468px" }}>
            <form onSubmit={handleSubmit}>
              <Stack
                spacing={4}
                p="1rem"
                backgroundColor="whiteAlpha.900"
                boxShadow="md"
              >
                {errMsg && 
                <Box my={4}>
                <Alert status="error" borderRadius={4}>
                    <AlertIcon />
                    <AlertDescription>{errMsg}</AlertDescription>
                </Alert>
                </Box>
                }
                  {/* UserName */}
                <FormControl isRequired>
                  <InputGroup>
                    <InputLeftElement
                      pointerEvents="none"
                      children={<CFaUserAlt color="gray.300" />}
                    />
                    <Input  type="text" placeholder="Username" name='username' onChange={
                (e) => {
                    setUsername(e.target.value);
                }}></Input>
                  </InputGroup>
                </FormControl>
                  {/* Password */}
                  <FormControl isRequired>
                  <InputGroup>
                    <InputLeftElement
                      pointerEvents="none"
                      color="gray.300"
                      children={<CFaLock color="gray.300" />}
                    />
                    <Input 
                      type={showPassword ? "text" : "password"}
                      placeholder="Password" name="password" onChange={(e)=>{
                        setPassword(e.target.value);
                        }}></Input>
                    <InputRightElement width="4.5rem">
                      <Button h="1.75rem" size="sm" onClick={handleShowClick}>
                        {showPassword ? "Hide" : "Show"}
                      </Button>
                    </InputRightElement>
                  </InputGroup>
                </FormControl>
                <Button
                  borderRadius={0}
                  type="submit"
                  variant="solid"
                  colorScheme="teal"
                  width="full"
                  
                >
                { loading ? (<CircularProgress isIndeterminate size="24px" color="teal" />)
                         : ("Login")
                }
                  
                </Button>
              </Stack>
            </form>
          </Box>
        </Stack>
        <Box>
          New to us?{" "}
          <Link as={ReactLink} color="teal.500" to="/register">
            Sign Up
          </Link>
        </Box>
      </Flex>
      </>
     );
}

/* original code

    <div>
        <h1 className="try">Login Page</h1>    
        {errMsg && <h1>{errMsg}</h1>}
        <form onSubmit={handleSubmit} >
            Username - <input required type="text"  name='username' onChange={
                (e) => {
                    setUsername(e.target.value);
                }}></input>
            Password - <input required type="text" name="password" onChange={(e)=>{
                setPassword(e.target.value);
            }}></input>
            <button type="submit">Login</button>
        </form>
        <Link to="/register">Register</Link> 
    </div>
    Can be used to show forgot password
<FormHelperText textAlign="right">
<Link>forgot password?</Link>
</FormHelperText>

* */
 
export default LoginPage;