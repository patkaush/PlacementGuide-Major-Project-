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
    color,
  } from '@chakra-ui/react';
import { AlertDialog, AlertDialogBody, AlertDialogContent, AlertDialogFooter, AlertDialogHeader, AlertDialogOverlay,  useDisclosure } from "@chakra-ui/react";
import { useRef } from "react";
import { DeleteIcon } from "@chakra-ui/icons";

const UserPage = (props) => {
  const { isOpen, onOpen, onClose } = useDisclosure()
  let [bodydata,setBodydata] = useState('');
  let [commentUser,setUserName] = useState();
  const cancelRef =useRef();
  let nav = useNavigate();
  let [comdata,setcomdata ] =useState('')

    let userId = props.id;
    console.log(userId);
    let [info,setInfo] = useState(null);
    let confHeades = {
        headers:{
            "Authorization":"Bearer "+localStorage.getItem("token"),
            "id":localStorage.getItem("id"),
        }
    }
    let handleInputChange = (e) => {
      e.preventDefault();
      setcomdata(e.target.value)
    }
    let [favourites,setFavourites]=useState([]);
    useEffect(() => {
            axios
        .get(localStorage.getItem("url")+"/user/getUserProfile?id="+userId,confHeades)
        .then(res => {
            console.log(res.data);
            setInfo(res.data)
            favourites = []
            for(let fav in res.data.favourites){
              let x = {'id':fav,fav:res.data.favourites[fav]}
              favourites.push(x);
            }
            // setComments(info.comments)
            setFavourites(favourites)
            console.log(favourites)
        },[info])
        .catch(err => console.log("fetch" + err));
    
        },[]);
        let addComment = () => {
          const dataReq = {
            "userId":localStorage.getItem("id"),
            "companyId":userId,
            "data":comdata,
            "username": localStorage.getItem("username")
          }
          axios
          .post(localStorage.getItem("url")+"/company/addRemark",dataReq,confHeades)
          .then(res => {
            
              info.comments.unshift(res.data);
            setInfo(info);
            console.log(info);
            setcomdata("");
          })
          .catch(err => console.log("fetch" + err));
        }
        let deleteCompany = (id) =>{
// delete user code to be written
          axios
         .delete(localStorage.getItem("url")+"/user/delUser?id="+id,confHeades)
         .then(res => {
           alert("Success")
           nav("/profiles")
           })
         .catch(err => console.log("fetch" + err));
   
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
                {info &&info.username}
              </Heading>
              <Tooltip label='Delete User' fontSize='md'>
                    <IconButton
                      w='50px'
                      onClick={onOpen}
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
                  <AlertDialog isOpen={isOpen} leastDestructiveRef={cancelRef}onClose={onClose}>
                    <AlertDialogOverlay>
                    <AlertDialogContent>
                    <AlertDialogHeader fontSize='lg' fontWeight='bold'>
                      {info&& info.username}
                    </AlertDialogHeader>
        
                    <AlertDialogBody>
                      Are you sure? You can't undo this action afterwards.
                    </AlertDialogBody>
        
                    <AlertDialogFooter>
                    <Button ref={cancelRef} onClick={onClose}>
                      Cancel
                    </Button>
                    <Button colorScheme='red' onClick={() =>deleteCompany(userId)} ml={3}>
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

                <VStack align='left'>
                  <Text fontSize={'40px'} color={'blue.400'} fontWeight={'300'}>User Interests </Text>
                  <Box>
                      <SimpleGrid columns={{ base: 3, md: 5 }} spacing={4}>
                      {info && info.interests ?  (info.interests.length == 0) ?<Text>User Didn't Add Any Interests</Text> 
                      : info.interests.map((interest) => (
                        <Box key={info.interests.indexOf(interest)}
                        >
                          <Text fontSize='20px' color='cyan.800'>{interest}</Text>
                        </Box>
                      ))
                                                                              :<Text>User Didn't Add Any Interests</Text>}
                    </SimpleGrid>
                  </Box>
                </VStack>
              <Box>
                <Text
                  fontSize={{ base: '16px', lg: '18px' }}
                  color={'blue.400'}
                  fontWeight={'500'}
                  textTransform={'uppercase'}
                  mb={'4'}>
                  User Favourite Companies
                </Text>
                  <List spacing={2}>
                    { info && info.favourites && info.favourites.length == 0 ? <Text>User Dosent Have Any Favourites</Text> 
                      : 
                          <SimpleGrid columns={{ base: 2, md:6 }} spacing={4}>
                          {favourites.map((favourite) => (
                            <Box key={favourite.id}
                            >
                              <Text color='blue' fontSize='20px'><Link to={`/company/${favourite.id}` }>{favourite.fav}</Link></Text>
                            </Box>
                          ))}
                        </SimpleGrid>
    
                    }
                  </List>
              </Box>
              <Stack spacing={{ base: 4, sm: 6 }}>

                <Text
                    fontSize={{ base: '16px', lg: '18px' }}
                    color={useColorModeValue('blue.500', 'yellow.300')}
                    fontWeight={'500'}
                    textTransform={'uppercase'}
                    mb={'4'}>
                    User Comments 
                </Text>
                {info && info.companyData.length ?  info.companyData.map((comment) => (
                  <>
                      <Stack key={comment.comments.cid}p="4" boxShadow="lg" m="4" borderRadius="sm">
                      <Stack direction="row" alignItems="center">
                      <Text fontSize={{ base: 'lg' }} fontWeight="semibold"> {comment.companyName} <Text color={'gray.500'} fontSize={{base:'sm'}}>(Last Modified- {comment.date} )</Text></Text>
                      </Stack>
                    
                      <Stack
                        direction={{ base: 'column', md: 'row' }}
                        justifyContent="space-between">
                          <Flex>
                        <Text fontSize={{ base: 'lg' }} textAlign={'left'} >
                        { comment.comments.data}
                        </Text>
                        </Flex>
                      </Stack>
                    </Stack>
                    </>
                )) : <Text align='center'>User Didn't Add Any Comment</Text>
                }

              </Stack>

              <Stack spacing={{ base: 4, sm: 6 }} maxWidth='100%'>
                          <Text
                            fontSize={{ base: '16px', lg: '18px' }}
                            color={useColorModeValue('blue.500', 'yellow.300')}
                            fontWeight={'500'}
                            textTransform={'uppercase'}
                            mb={'4'}>
                            Remarks
                          </Text>
                        
                          <form >
                            <FormControl>
                              <Text mb='8px'>Add Your Remark</Text>
                                <Textarea
                                value={comdata}
                                onChange={handleInputChange}
                                size='sm'
                                isFullWidth='true'
                                ></Textarea>
                              <Button onClick={addComment}>Add Remark</Button>
                            </FormControl>
                          </form>
                          <Divider borderColor='red.800' variant='dashed' bg='blackAlpha.100'/>
                          {info &&info.comments.length ?  info.comments.map((comment) => (
                            <>
                                <Stack key={comment.cid}p="4" boxShadow="lg" m="4" borderRadius="sm">
                                <Stack direction="row" alignItems="center">
                                  <Avatar size='md'name={comment.userName || comment.username}></Avatar>
                                  <Text fontSize={{ base: 'lg' }} fontWeight="semibold">{comment.userName || comment.username} <Text color={'gray.500'} fontSize={{base:'sm'}}>(Last Modified- {comment.strDate || comment.date} )</Text></Text>

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
 
export default UserPage;