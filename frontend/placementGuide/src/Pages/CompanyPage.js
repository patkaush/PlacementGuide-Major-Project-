import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router";

import { Link } from "react-router-dom";
import Navbar from "./Navbar";
import {
    Box,
    chakra, 
    Container,
    Stack,
    Text,
    Image,
    Flex,
    VStack,
    Button,
    Heading,
    SimpleGrid,
    StackDivider,
    useColorModeValue,
    VisuallyHidden,
    List,
    ListItem,
    Grid,
    FormControl,
    FormLabel,
    FormHelperText,
    Input,
    Textarea,
    Divider,
    Avatar,
    HStack,
    GridItem,
    IconButton,
    Tooltip,
    Modal,
    ModalOverlay,
    ModalContent,
    ModalHeader,
    ModalCloseButton,
    ModalBody,
    ModalFooter,
  } from '@chakra-ui/react';
import { AlertDialog, AlertDialogBody, AlertDialogContent, AlertDialogFooter, AlertDialogHeader, AlertDialogOverlay,  useDisclosure } from "@chakra-ui/react";
import { useRef } from "react";

import { FaInstagram, FaTwitter, FaYoutube } from 'react-icons/fa';
import { AddIcon, DeleteIcon, EmailIcon, SearchIcon } from "@chakra-ui/icons";
function addToFavourites(id){
    let confHeaders = {
        headers:{
            "Authorization":"Bearer "+localStorage.getItem("token"),
            "id":localStorage.getItem("id"),
            "companyid":id
        }
    }
    const devUrl = "http://localhost:8081/placementGuide/user/addCompany/";
    const devployUrl = "https://noob0me.herokuapp.com/user/addCompany/"

    axios
    .post(localStorage.getItem("url")+"/user/addCompany/",{'':''},confHeaders)
    .then(res => {
        alert(res.data)
    })
    .catch(err => alert(err.response.data))
    
}
const CompanyPage = (props) => {
  const { isOpen, onOpen, onClose } = useDisclosure()
  const del = useDisclosure();
  let [bodydata,setBodydata] = useState('');
  let [commentUser,setUserName] = useState();
  const cancelRef =useRef();
  let nav = useNavigate();
    let companyId = props.id;
    let [comdata,setcomdata ] =useState('')
    let [info,setInfo] = useState(null);
    let [comments, setComments] = useState(null);
    let [resourceUrl,setResourceUrl] = useState("");

    let confHeades = {
        headers:{
            "Authorization":"Bearer "+localStorage.getItem("token"),
            "id":localStorage.getItem("id"),
        }
    }
    let handleLinkChange = (e) => {
      e.preventDefault();
      setResourceUrl(e.target.value)
    }
    
    let handleInputChange = (e) => {
      e.preventDefault();
      setcomdata(e.target.value)
    }
    const devUrl = "http://localhost:8081/placementGuide/company/retrieve?id=";
    const devployUrl = "https://noob0me.herokuapp.com/company/retrieve?id="

    
    useEffect(() => {
        axios
    .get(localStorage.getItem("url")+"/company/retrieve?id="+companyId,confHeades)
    .then(res => {
        setInfo(res.data);
        // setComments(info.comments)
    },[info])
    .catch(err => console.log("fetch" + err));

    },[]);
  let addComment = () => {
      const dataReq = {
        "userId":localStorage.getItem("id"),
        "companyId":companyId,
        "data":comdata,
        "username": localStorage.getItem("username")
      }
      axios
      .post(localStorage.getItem("url")+"/company/addComment",dataReq,confHeades)
      .then(res => {
        
          info.comments.unshift(res.data);
        setInfo(info);
        
        setcomdata("");
        console.log(dataReq.data);
      })
      .catch(err => console.log("fetch" + err));
    }
  let addResource = () => {
    if(resourceUrl){
      try{
         new URL(resourceUrl);
      }catch(_){
          alert("Invalid URL...Please Enter Proper Url");
          return;
      }
    }
      const dataReq = {
        "userId":localStorage.getItem("id"),
        "companyId":companyId,
        "resourceData": resourceUrl,
        "username":localStorage.getItem("username")
      }

       axios
      .post(localStorage.getItem("url")+"/company/addResource ",dataReq,confHeades)
      .then(res => {
          info.resources.push(dataReq);
          setInfo(info);
          setResourceUrl("");

        })
      .catch(err => console.log("fetch" + err));
    }
    let deleteCompany = (id) =>{

       axios
      .delete(localStorage.getItem("url")+"/company/delCompany?id="+id,confHeades)
      .then(res => {
        nav("/")
        })
      .catch(err => console.log("fetch" + err));

    }
    // if(info)
    // console.log(info.comments);
    let submitEmail = (user) =>{
      console.log(user)
      let dataObj = {
        body:bodydata,
        username: user
      }
      console.log(dataObj)
      axios
      .post(localStorage.getItem("url")+"/user/sendEmail",dataObj,confHeades)
      .then(res => {
        alert(res.data)
        })
      .catch(err => console.log("fetch" + err));
        
      onClose();
    }
    return ( 
        <>
        <Navbar/>
        <Container maxW={'7xl'}>
        
        <Grid
          columns={{ base: 1 }}
          spacing={{ base: 8, md: 10 }}
          py={{ base: 18, md: 24 }}>
          <Stack spacing={{ base: 6, md: 10 }}>
            <Stack direction={{ base: 'column', md: 'row' }}>

              <Heading
                lineHeight={1.1}
                fontWeight={600}
                fontSize={{ base: '2xl', sm: '4xl', lg: '5xl' }}>
                {info &&info.name}
              </Heading>
                {localStorage.getItem("admin")=="true" ? 
                    <Tooltip label='Delete Company' fontSize='md'>
                    <IconButton
                      w='50px'
                      onClick={del.onOpen}
                      backgroundColor='white'
                      py={'7'}
                      icon={<DeleteIcon />}
                      size='lg'
                      _hover={{
        
                        transform: 'translateY(2px)',
                        boxShadow: 'lg',
                      }}>
                    </IconButton>
                    </Tooltip>

                  : 
                    <Tooltip label='Add to favourites' fontSize='md'>
                    <IconButton
                    w='10px'
                    onClick={() => addToFavourites(companyId)}
                    backgroundColor='white'
                    py={'7'}
                    icon={<AddIcon />}
                    _hover={{
      
                      transform: 'translateY(2px)',
                      boxShadow: 'lg',
                    }}>
                  </IconButton>
                  </Tooltip>

                  }

          <AlertDialog
            isOpen={del.isOpen}
            leastDestructiveRef={cancelRef}
            onClose={del.onClose}
          >
            <AlertDialogOverlay>
              <AlertDialogContent>
                <AlertDialogHeader fontSize='lg' fontWeight='bold'>
                  {info&& info.name}
                </AlertDialogHeader>
    
                <AlertDialogBody>
                  Are you sure? You can't undo this action afterwards.
                </AlertDialogBody>
    
                <AlertDialogFooter>
                <Button ref={cancelRef} onClick={del.onClose}>
                  Cancel
                </Button>
                <Button colorScheme='red' onClick={() =>deleteCompany(info.id)} ml={3}>
                  Delete
                </Button>
    
                </AlertDialogFooter>
              </AlertDialogContent>
            </AlertDialogOverlay>
          </AlertDialog>
    
            </Stack>
  
            <Stack
                spacing={{ base: 4, sm: 6 }}
                direction={'column'}
                divider={
                  <StackDivider
                    borderColor={useColorModeValue('blue.200', 'gray.600')}
                  />
              }>
              <VStack spacing={{ base: 4, sm: 6 }}>
                <Text fontSize={'lg'}>
                     { info &&info.description}
                </Text>
              </VStack>
              <Box>
                <Text
                  fontSize={{ base: '16px', lg: '18px' }}
                  color={useColorModeValue('blue.400', 'yellow.300')}
                  fontWeight={'500'}
                  textTransform={'uppercase'}
                  mb={'4'}>
                  Misc
                </Text>
  
                <SimpleGrid columns={{ base: 1, md: 2 }} spacing={10}>
                  <List spacing={2}>
                    {info && info.interview_difficulty &&<ListItem>Interview Difficulty- {info.interview_difficulty}</ListItem>}
                    {info && info.interview_duration && <ListItem>Interview Duration- {info.interview_duration}</ListItem>}
                    {info && info.rating && <ListItem>Interview Rating- {info.rating}</ListItem>}
                    {info && info.reviews && <ListItem>Interview Review- {info.reviews}</ListItem>  }
                  </List>
                </SimpleGrid>
              </Box>

                <Stack spacing={{ base: 4, sm: 6 }}>

                  <Text
                      fontSize={{ base: '16px', lg: '18px' }}
                      color={useColorModeValue('blue.500', 'yellow.300')}
                      fontWeight={'500'}
                      textTransform={'uppercase'}
                      mb={'4'}>
                      Related Links
                  </Text>
                  {info &&info.resources && info.resources.map((res) => (
                      <Box key={res.id}
                      >
                        <Text color='teal.500'>
                          <a  href={ res.resourceData}>
                          { res.resourceData}
                          </a>
                        </Text>
                      <Text> </Text>
                      </Box>
                      ))
                      }

                    <form >
                      <FormControl>
                    <Text mb='8px'>Add Your Resources</Text>
                    <Input  type="text" placeholder="Enter Url" name='url' value={resourceUrl} onChange={handleLinkChange}></Input>
                      <Button onClick={addResource}>Add Resource</Button>
                      </FormControl>
                    </form>
                </Stack>
              

              <Stack spacing={{ base: 4, sm: 6 }} maxWidth='100%'>
                          <Text
                            fontSize={{ base: '16px', lg: '18px' }}
                            color={useColorModeValue('blue.500', 'yellow.300')}
                            fontWeight={'500'}
                            textTransform={'uppercase'}
                            mb={'4'}>
                            Comments
                          </Text>
                        
                          <form >
                            <FormControl>
                              <Text mb='8px'>Add Your Comment:</Text>
                                <Textarea
                                value={comdata}
                                onChange={handleInputChange}
                                size='sm'
                                isFullWidth='true'
                                ></Textarea>
                              <Button onClick={addComment}>Add Comment:</Button>
                            </FormControl>
                          </form>
                          <Divider borderColor='red.800' variant='dashed' bg='blackAlpha.100'/>
                          {info &&info.comments.length ?  info.comments.map((comment) => (
                            <>
                            <Modal closeOnOverlayClick={false} isOpen={isOpen} onClose={onClose} size='lg'>
                              <ModalOverlay />
                              <ModalContent >
                                <ModalHeader> Send Email to {commentUser}</ModalHeader>
                                <ModalCloseButton />
                                <ModalBody pb={6} >
                                <Textarea placeholder='Enter Data' h='400px' value={bodydata} onChange={(e)=>{setBodydata(e.target.value)}}resize='none'/>
                                </ModalBody>
                                <ModalFooter>
                                  <Button onClick={() => submitEmail(commentUser)} colorScheme='blue' mr={3}>
                                    Send
                                  </Button>
                                    <Button onClick={onClose}>Cancel</Button>
                                </ModalFooter>
                              </ModalContent>
                            </Modal>
                                <Stack key={comment.cid}p="4" boxShadow="lg" m="4" borderRadius="sm">
                                <Stack direction="row" alignItems="center">
                                  <Avatar size='md'name={comment.username}></Avatar>
                                  <Text fontSize={{ base: 'lg' }} fontWeight="semibold">{comment.username} <Text color={'gray.500'} fontSize={{base:'sm'}}>(Last Modified- {comment.date} )</Text></Text>
                                  <Tooltip label='Send An Email To The User' fontSize='md'>
                                    <EmailIcon color="cyan.500" w={8} h={8}  onClick={() => {setUserName(comment.username);onOpen()}}/>
                                  </Tooltip>                                

                                </Stack>
                              
                                <Stack
                                  direction={{ base: 'column', md: 'row' }}
                                  justifyContent="space-between">
                                    <Flex>
                                  <Text fontSize={{ base: 'lg' }} textAlign={'left'} >
                                  { comment.data}
                                  </Text>
                                  </Flex>
                                </Stack>
                              </Stack>
                              </>
                          )) : <Text align='center'>Oops This Place is Empty...Add A Comment</Text>
                          }
                  </Stack>

              
            </Stack>
            <Divider borderColor='blue'  bg='blackAlpha.100'/>
  
          </Stack>
        </Grid>
      </Container>     
      </>   
     );
}
 
export default CompanyPage;



/*
        <div>
            <Navbar/>
            <h1>Company Details </h1>
            {info &&
            <ol>
                <li> Name - {info.name}</li>
                <li> description - {info.description}</li>
                <li> Interview Difficulty- {info.interview_difficulty}</li>
                <li> Interview Duration- {info.interview_duration}</li>
                <li> <button onClick={() => addToFavourites(id)}>Add to favourites</button> </li>
            </ol>
            }
        </div>

https://chakra-templates.dev/page-sections/productDetails - for template
{info &&info.comments && info.comments.map((comment) => (
  <Box key={comment.cid}
  borderColor='red.800'
  border= '2px solid'
  divider={
    <StackDivider
      borderColor='red.400'
    />
  } 
  >

  </Box>
  ))
  }

  
            <IconButton
              rounded={'none'}
              w='10px'
              mt={8}
              size={'lg'}
              onClick={() => addToFavourites(companyId)}
              py={'7'}
              icon={<SearchIcon />}
              bg={useColorModeValue('gray.900', 'gray.50')}
              color={useColorModeValue('white', 'gray.900')}
              textTransform={'uppercase'}
              _hover={{
                transform: 'translateY(2px)',
                boxShadow: 'lg',
              }}>
            </IconButton>
  
*/

