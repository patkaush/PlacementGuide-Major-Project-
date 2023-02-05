import CompanyPage from './Pages/CompanyPage';
import DefaultPage from './Pages/DefaultPage';
import FavourtiesPage from './Pages/FavouritesPage';
import HomePage from './Pages/HomePage';
import InterestsPage from './Pages/InterestsPage';
import LogoutPage from './Pages/LogoutPage';
import ImportPage from './Pages/ImportPage';
import ProfilesPage from './Pages/ProfilesPage'
import { useParams } from "react-router-dom";
import UserPage from './Pages/UserPage';

const AuthRoutes = (props) => {
    let source = props.name;
    let {id} = useParams();
    console.log("Got It "+id);
    const publicRoutes ="";
    const privateRoutes ="" ;
    let resource = <DefaultPage/> ;
    if(localStorage.getItem("token")){
        switch(source){
            case "home":resource =  <HomePage/>;break;
            case "logout":resource =  <LogoutPage/>;break;
            case "interests":resource =  <InterestsPage/>;break;
            case "favourites":resource =  <FavourtiesPage/>;break;
            case "company":{
                resource = <CompanyPage id={id}/>;break;
            }
            case "import": resource = localStorage.getItem("admin")=="true" ? <ImportPage/> : <DefaultPage/>;break;
            case "profiles":resource = localStorage.getItem("admin")=="true"? <ProfilesPage/>:<DefaultPage/>;break;
            case "userprofile":resource = localStorage.getItem("admin")=="true" ? <UserPage id={id} />:<DefaultPage/>;break;
        }
    }
    console.log(resource)
    return (  
            resource
    );
}
 
export default AuthRoutes;