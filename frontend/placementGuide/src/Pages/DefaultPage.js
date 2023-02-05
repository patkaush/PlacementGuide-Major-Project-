import { useNavigate } from "react-router";
import { Link } from "react-router-dom";

const DefaultPage = () => {
    let navigate = useNavigate();
    return (  
        <div>
            <h1>"Oops You entered Dev Page...Login to Acess you Content"</h1>
            <Link to="/login">Click here</Link>
        </div>
    );
}
 
export default DefaultPage;