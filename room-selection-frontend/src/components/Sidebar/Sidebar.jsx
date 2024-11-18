import React from 'react'
import "./Sidebar.css"
import {Link} from "react-router-dom";
const Sidebar = () => {
  return (
    <div className="sidebar">
      <h2>Name</h2>
      <ul>
        <li>Profile</li>
          <Link to= '/booking/form'><li>Booking</li></Link>
        <li>Rooms</li>
      </ul>
    </div>
  );
};

export default Sidebar
