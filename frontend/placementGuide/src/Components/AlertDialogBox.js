import { AlertDialog, AlertDialogBody, AlertDialogContent, AlertDialogFooter, AlertDialogHeader, AlertDialogOverlay, Button, useDisclosure } from "@chakra-ui/react";
import { useRef } from "react";

const AlertDialogBox = (props) => {
    let title = props.title;
    let body = props.body;

    const { isOpen, onOpen, onClose } = useDisclosure()
    const cancelRef =useRef()
  
    return (
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
                {title}
              </AlertDialogHeader>
  
              <AlertDialogBody>
                  {body}
              </AlertDialogBody>
  
              <AlertDialogFooter>
              <Button ref={cancelRef} onClick={onClose}>
                Cancel
              </Button>
              <Button colorScheme='red' onClick={onClose} ml={3}>
                Delete
              </Button>
  
              </AlertDialogFooter>
            </AlertDialogContent>
          </AlertDialogOverlay>
        </AlertDialog>
      </div>
    )
  };
 
export default AlertDialogBox;
