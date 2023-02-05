import { useState } from "react";
import axios from 'axios';
import { useNavigate } from "react-router-dom";
import { Link as ReactLink} from "react-router-dom";
import { FiMail } from "react-icons/fi";

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
import '../Styles/loginpage.css';
import { FaUserAlt, FaLock, FaBeer } from "react-icons/fa";

const RegisterPage = () => {
    const CFaUserAlt = chakra(FaUserAlt);
    const CFaLock = chakra(FaLock);
    const CFiMail = chakra(FiMail);
    
    const [email,setEmail] = useState('');
    const [username,setUsername] = useState('');
    const [password,setPassword] = useState('');
    const [cpassword,setCPassword] = useState('');
    const [loading,setLoading] = useState(false);
    const [showPassword, setShowPassword] = useState(false);
    const handleShowClick = () => setShowPassword(!showPassword);
    const [errMsg,setErrMsg] = useState(null);
    const devUrl = "http://localhost:8081/placementGuide/user/register";
    const devployUrl = "https://noob0me.herokuapp.com/user/register"
    const history = useNavigate();
    const DataObj = {
        "email":email,
        "username":username,
        "password":password,
        "admin" : "false"
    };
    const handleSubmit = (e) => {
        e.preventDefault();
        if(password.localeCompare(cpassword) ){
            setErrMsg("Password and Confirm Password are not matching");
            return;
        }
        setLoading(true);


        axios
        .post(localStorage.getItem("url")+"/user/register",DataObj)
        .then(res => {
            setLoading(false);
            console.log(res.data.id)
            localStorage.setItem("id",res.data.id);
            localStorage.setItem("token",res.data.token);
            localStorage.setItem("username",username);
            history("/");
        })
        .catch(err =>{
            setLoading(false);
            setErrMsg(err.response.data);
            console.log("Error bro "+err);
        })
        
    }


    return (  
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

                <FormControl isRequired>
                  <InputGroup>
                    <InputLeftElement
                      pointerEvents="none"
                      children={<CFiMail color="gray.300" />}
                    />
                    <Input  type="email" placeholder="Email" name='email' onChange={
                (e) => {
                    setEmail(e.target.value);
                }}></Input>
                  </InputGroup>
                </FormControl>
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
                <FormControl isRequired>
                  <InputGroup>
                    <InputLeftElement
                      pointerEvents="none"
                      color="gray.300"
                      children={<CFaLock color="gray.300" />}
                    />
                    <Input 
                      type={showPassword ? "text" : "password"}
                      placeholder="Confirm Password" name="cpassword" onChange={(e)=>{
                        setCPassword(e.target.value);
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
                         : ("Register")
                }
                  
                </Button>
              </Stack>
            </form>
          </Box>
        </Stack>
        <Box>
          Existing User?{" "}
          <Link as={ReactLink} color="teal.500" to="/login">
            Sign In
          </Link>
        </Box>
      </Flex>
      

    );
}
 
export default RegisterPage;






/*
<div className="registerPage">
<h1> Register Page</h1>
<form onSubmit={handleSubmit}>
    Email - <input type="text" name = "email"
    onChange={(e) => {
        setEmail(e.target.value);
    }}
    ></input>                
    Username - <input type="text" name = "username"
    onChange={(e) => {
        setUsername(e.target.value);

    }}
    ></input>                
    Password - <input type="text" name = "password"
    onChange={(e) => {
        setPassword(e.target.value);
    }}
    ></input>               
    <button type="submit" >Submit</button> 
</form>
    
</div>
 */