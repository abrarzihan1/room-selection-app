
import React from 'react';

import './Navbar.css';


const Navbar = () => {
    return (
        <nav className='container'>
            <h2>TeachSpace</h2>
            <ul>
                <li>Home</li>
                <li>Booking</li>
                <li>About Us</li>
                <li><button className='btn'>Login </button></li>
            </ul>
        </nav>


    )
}

export default Navbar




