import React from 'react'
import "./Sidebar.css"
import { Link } from "react-router-dom";

const Sidebar = () => {
    return (
        <div className="sidebar">
            <div className="sidebar-item">
                <Link className="sidebar-link" to="/dashboard">Dashboard</Link>
            </div>
            <div className="sidebar-item">
                <Link className={"sidebar-link"} to="/profile">Profile</Link>
            </div>
            <div className="sidebar-item">
                <Link className="sidebar-link" to='/booking'>Booking</Link>
            </div>
            <div className="sidebar-item">
                <Link className={"sidebar-link"} to="/room">Room</Link>
            </div>
        </div>
    );
};

export default Sidebar;
