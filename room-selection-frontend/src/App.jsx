
import { BrowserRouter as Router, Routes, Route, } from 'react-router-dom';
import  Navbar  from './components/Navbar/Navbar'
import Signin from './Pages/Signin'
import Admin from './Pages/Admin'
import Home from './Pages/Home'
import Signup from './Pages/Signup'
import Dashboard from './components/Dashboard/Dashboard'
import Sidebar from './components/Sidebar/Sidebar'


const App = () => {

  return(
    <div>
<Navbar/>
 <Routes>
<Route path='/home' element ={<Home/>}/>
<Route path='/login' element = {<Signin/>}/>
<Route path='/signup' element ={<Signup/>}/>
<Route path='admin' element ={<Admin/>}/>
 </Routes>
    </div>
    
  )
}
export default App

