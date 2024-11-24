import React from 'react'
import "./Sidebar.css"
import {Link} from "react-router-dom";

const Sidebar = () => {
    return (
        <div className="sidebar">
            <p>
                <Link className="sidebar-link" to="/dashboard">Dashboard</Link>
            </p>
            <p>
                <Link className={"sidebar-link"} to={"/profile"}>Profile</Link>
            </p>
            <p>
                <Link className="sidebar-link" to='/booking'>Booking</Link>
            </p>
            <p>
                <Link className={"sidebar-link"} to={"/room"}>Room</Link>
            </p>
        </div>
    );
};

export default Sidebar
