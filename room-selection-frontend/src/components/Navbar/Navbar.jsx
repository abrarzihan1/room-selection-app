  
  import React from 'react';
 import { Link ,NavLink } from 'react-router-dom';
  import './Navbar.css';


  const Navbar = () => {
    return (
      <nav className='container'>
          <h2>TeachSpace</h2>
        <ul>
          <NavLink to= '/Home'><li>Home</li></NavLink>  
          <NavLink to= '/Login'> <li>Booking</li></NavLink>
          <Link to= '/about us'><li>About Us</li></Link>
            <Link to = '/login'><button className='btn'>Login </button></Link>
        </ul>
       </nav>

    
    )
  } 

  export default Navbar




