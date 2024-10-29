  
  import React from 'react'
  import { NavLink } from 'react-router-dom'
  import './Navbar.css'
  import logo from '../assets/logo.png'


  const Navbar = () => {
    return (
      <nav className='container'>
        <NavLink to="Home">
        <img src={logo} alt=""  className='logo'/>
        </NavLink>
    
        <ul>
          <li>
    <NavLink to="Home">Home</NavLink>
          </li>
          <li><NavLink to="login">Booking</NavLink></li>
        
          <li><NavLink to="About Us">About Us</NavLink>
</li>
  <li> <button className='btn'>Contact us </button></li>
  </ul>
  
      
    </nav>

    
    )
  } 

  export default Navbar




