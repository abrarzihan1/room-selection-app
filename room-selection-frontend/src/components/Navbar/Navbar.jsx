  
  import React from 'react';
 import { Link } from 'react-router-dom';
  import './Navbar.css';


  const Navbar = () => {
    return (
      <nav className='container'>
          <h2>TeachSpace</h2>
        <ul>
          <Link to= '/Home'><li>Home</li></Link>  
          <Link to= '/Login'> <li>Booking</li></Link>
          <Link to= '/about'><li>About Us</li></Link>
            <Link to = '/login'><button className='btn'>Login </button></Link>
        </ul>
       </nav>

    
    )
  } 

  export default Navbar




