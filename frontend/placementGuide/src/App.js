import {BrowserRouter as Router,Routes,Route} from 'react-router-dom';
import RegisterPage from './Pages/RegisterPage';
import LoginPage from './Pages/LoginPage';
import HomePage from './Pages/HomePage';
import LogoutPage from './Pages/LogoutPage';
import AuthRoutes from './AuthRoutes';
import InterestsPage from './Pages/InterestsPage';
import FavourtiesPage from './Pages/FavouritesPage';
import Test from './Pages/Test';

function App() {
  localStorage.setItem("url","http://localhost:8081/placementGuide");
  return (
        <Router>
          <Routes>
            <Route path="/test" element={<Test/>}/>
            <Route path="/" element={<AuthRoutes name="home"/>}/>
            <Route path="/profiles" element={<AuthRoutes name="profiles"/>}/>
            <Route  path="/interests" element={<AuthRoutes name="interests"/>}/>
            <Route path="/company/:id" element={<AuthRoutes name="company" />} />                 
            <Route path="/import" element={<AuthRoutes name="import" />} />                 
            <Route  path="/favourites" element={<AuthRoutes name="favourites"/>}/>
            <Route path="/logout" element={<AuthRoutes name="logout"/>}/>
            <Route path="/user/getUserProfile/:id" element={<AuthRoutes name="userprofile"/>}/>
            <Route path="/login" element={<LoginPage/>}/>
            <Route  path="/register" element={<RegisterPage/>}/>
          </Routes>
        </Router>

  );
}
export default App;
