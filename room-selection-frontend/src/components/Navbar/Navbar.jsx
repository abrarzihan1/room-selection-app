import React, { useState, useEffect, useRef } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './Navbar.css';

const Navbar = ({ isAuthenticated, onLogout }) => {
    const [showDropdown, setShowDropdown] = useState(false);
    const dropdownRef = useRef(null);
    const navigate = useNavigate();

    const handleLogout = () => {
        localStorage.removeItem('token');
        onLogout();
        navigate('/login');
    };

    const toggleDropdown = () => {
        setShowDropdown((prev) => !prev);
    };

    useEffect(() => {
        const handleClickOutside = (event) => {
            if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
                setShowDropdown(false);
            }
        };

        document.addEventListener('mousedown', handleClickOutside);
        return () => {
            document.removeEventListener('mousedown', handleClickOutside);
        };
    }, []);

    return (
        <nav className='nav-container'>
            <h2 className="teachspace">TeachSpace</h2>
            <ul>
                <Link to='/'>
                    <li className={"nav-link"}>Home</li>
                </Link>
                <Link to='/booking'>
                    <li className={"nav-link"}>Booking</li>
                </Link>
                <Link to='/about'>
                    <li className={"nav-link"}>About Us</li>
                </Link>
                {isAuthenticated ? (
                    <div className="dropdown" ref={dropdownRef}>
                        <button className="btn" onClick={toggleDropdown}>
                            User
                        </button>
                        {showDropdown && (
                            <ul className="dropdown-menu">
                                <li onClick={() => navigate('/dashboard')}>Dashboard</li>
                                <li className={"dropdown-item-sidebar"} onClick={() => navigate('/profile')}>
                                    Profile
                                </li>
                                <li className={"dropdown-item-sidebar"} onClick={() => navigate('/booking')}>
                                    Booking
                                </li>
                                <li className={"dropdown-item-sidebar"} onClick={() => navigate('/room')}>
                                    Room
                                </li>
                                <li onClick={handleLogout}>Logout</li>
                            </ul>
                        )}
                    </div>
                ) : (
                    <Link to='/login'>
                        <button className='btn'>Login</button>
                    </Link>
                )}
            </ul>
        </nav>
    );
};

export default Navbar;
