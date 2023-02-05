import { Link as ReactLink} from "react-router-dom";
import {
    Box,
    Flex,
    Avatar,
    HStack,
    Link,
    IconButton,
    Button,
    Menu,
    MenuButton,
    MenuList,
    MenuItem,
    MenuDivider,
    useDisclosure,
    useColorModeValue,
    Stack,
    Text,
  } from '@chakra-ui/react';
import React from "react";
import { HamburgerIcon, CloseIcon } from '@chakra-ui/icons';
const Navbar = () => {
  const { isOpen, onOpen, onClose } = useDisclosure();
  const Links = localStorage.getItem("admin")=="true" ? 
  [ { id: 1 , title:"Home",route:"/"} ,
    { id: 3 , title:"User Profiles",route:"/profiles"},
    { id: 2 , title:"Upload Data",route:"/import"},
    { id: 4 , title:"Logout",route:"/login"}
  ]
  : [ { id: 1 , title:"Home",route:"/"} ,
      { id: 2 , title:"Change Interests",route:"/interests"},
      { id: 3 , title:"Favourite Companies",route:"/favourites"},
      { id: 4 , title:"Logout",route:"/login"}
  ];

const NavLink = ({route,children}) => {
    
    return React.createElement(Link, { px: 2, py: 1, rounded: 'md', as:ReactLink,
    _hover: {
    textDecoration: 'none',
    bg: useColorModeValue('gray.200'),
    }
    , to:  route }, children);
}


    return ( 
        <>
        <Box bg={useColorModeValue('cyan.800')} px={4} color='white'>
          <Flex h={16} alignItems={'center'} justifyContent={'space-between'}>
            <Text>Welcome {localStorage.getItem("username")}</Text>
            <IconButton
              size={'md'}
              icon={isOpen ? <CloseIcon /> : <HamburgerIcon />}
              aria-label={'Open Menu'}
              display={{ md: 'none' }}
              onClick={isOpen ? onClose : onOpen}
            />
            <HStack spacing ={8} alignItems={'center'}>
              <HStack
                as={'nav'}
                spacing={4}
                pos = "absolute"
                right= {0}
                display={{ base: 'none', md: 'flex' }}>
                {Links.map((link) => (
                  <NavLink key={link.id} route={link.route}>{link.title}</NavLink>
                  ))}
              </HStack>
            </HStack>
            <Flex alignItems={'center'}>
              <Menu>
                <MenuButton
                  as={Button}
                  rounded={'full'}
                  variant={'link'}
                  cursor={'pointer'}
                  minW={0}>
                </MenuButton>
              </Menu>
            </Flex>
          </Flex>
  
          {isOpen ? (
            <Box pb={4} display={{ md: 'none' }}>
              <Stack as={'nav'} spacing={4}>
              {Links.map((link) => (
                  <NavLink key={link.id} route={link.route}>{link.title}</NavLink>
                  ))}
              </Stack>
            </Box>
          ) : null}
        </Box>
  
      </>        

     );
}
 
export default Navbar;


/*
        <div>
            <nav style={{"padding-left":"600px"}}>
            <Link to="/" >Home</Link>
            <>&nbsp;</>
            <Link to = "/interests">Change Interests</Link>
            <>&nbsp;</>
            <Link to ="/favourites">Favourite Companies</Link>                
            <>&nbsp;</>
            <Link to = "/login">Logout</Link>
            </nav>
        </div>


 */