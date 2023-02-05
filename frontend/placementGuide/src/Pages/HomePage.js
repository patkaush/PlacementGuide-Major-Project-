import { Heading } from "@chakra-ui/react";
import axios from "axios";
import { Link } from "react-router-dom";
import ShowCompanies from "../Components/ShowCompanies";
import Navbar from "./Navbar";

function HomePage()  {
    

    return ( 
        <div>
            <Navbar/>
            <ShowCompanies/>
        </div>

     );
}
 
export default HomePage;