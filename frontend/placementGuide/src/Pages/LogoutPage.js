import axios from "axios";
import { useNavigate } from "react-router";

function DelToken(){
}

const LogoutPage = () => {
    const nav = useNavigate();
    let config = {
        headers :{
            "Authorization":"Bearer "+localStorage.getItem("token"),
            "id":localStorage.getItem("id"),
        }
    }
    const devUrl = "http://localhost:8081/placementGuide/user/logout";
    const devployUrl = "https://noob0me.herokuapp.com/user/logout"
    axios
    .delete(localStorage.getItem("url")+"/user/logout",config)
    .then(res => {
        console.log(res.data)
        localStorage.removeItem('id');
        localStorage.removeItem('token');
        nav("/login");
        
    })
    .catch(err => console.log(err));

    return (
         
        <h1>You Logged out</h1>
        
      );
}
 
export default LogoutPage;