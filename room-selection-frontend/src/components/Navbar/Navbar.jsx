import React from 'react';
import { Link } from 'react-router-dom';
import './Navbar.css';
  const Navbar = () => {
    return (
      <nav className='container'>
          <h2 className={"teachspace"}>TeachSpace</h2>
        <ul>
          <Link to= '/home'><li>Home</li></Link>
          <Link to= '/login'> <li>Booking</li></Link>
          <Link to= '/about'><li>About Us</li></Link>
            <Link to = '/login'><button className='btn'>Login </button></Link>
        </ul>
       </nav>
    )
  } 

  export default Navbar




