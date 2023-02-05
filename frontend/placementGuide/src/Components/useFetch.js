import axios from "axios";
import { useEffect, useState } from "react";

// send url and component to monitor
const useFetch = (props) => {
    let endpoint = props.endpoint;
    let resource = props.resource;
    let [resData,setresData] = useState(null);
    let confHeades = {
        headers:{
            "Authorization":"Bearer "+localStorage.getItem("token"),
            "id":localStorage.getItem("id"),
        }
    }

    useEffect( () => {
        const abortCont =new  AbortController();
        axios
        .get(localStorage.getItem("url")+endpoint,confHeades)
        .then(res => {
            console.log("Hitting the API");
            setresData(res.data);
        })
        .catch(err => console.log(err))
        
        return () => abortCont.abort();

    },[resource])
    return ( 
        [resData,setresData]

     );
}
 
export default useFetch;