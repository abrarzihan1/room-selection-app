
import { BrowserRouter as Router, Routes, Route, } from 'react-router-dom';
import  Navbar  from './components/Navbar/Navbar'
import Signin from './Pages/Signin'

import Home from './Pages/Home'
import Signup from './Pages/Signup'
import Dashboard from './components/Dashboard/Dashboard'
import Sidebar from './components/Sidebar/Sidebar'
import AdminPage from './Pages/AdminPage';
import About from './Pages/About';


const App = () => {

  return(
    <div>
<Navbar/>
 <Routes>
 <Route path='' element ={<Home/>}/>
<Route path='/home' element ={<Home/>}/>
<Route path='/login' element = {<Signin/>}/>
<Route path='/signup' element ={<Signup/>}/>
<Route path='/admin' element ={<AdminPage/>}/>
<Route path= '/about' element = {<About/>}/>
 </Routes>
    </div>
    
  )
}
export default App

