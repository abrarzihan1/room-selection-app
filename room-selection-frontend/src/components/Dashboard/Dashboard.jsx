import React, {useEffect, useState} from 'react';
import './Dashboard.css';
import {useNavigate} from "react-router-dom";


const Dashboard = () => {
    const [bookings, setBookings] = useState([]);

    // // Fetch data from JSON file or API
    // useEffect(() => {
    //     // Replace this fetch with actual API call if required
    //     fetch('/data.json') // Ensure this path is correct for your setup
    //         .then((response) => response.json())
    //         .then((data) => setBookings(data));
    // }, []);

    return (
        <div className="dashboard">
            <h1>Dashboard</h1>
            <div className='title-row'>
                <div className='title-name'>
                    Room ID
                </div>
                <div className='title-name'>
                    Start time
                </div>
                <div className='title-name'>
                    End time
                </div>
                <div className='title-name'>
                    Date
                </div>
            </div>
            <div className='item-row'>
                <div className='item-name'>101</div>
                <div className='item-name'>12:00</div>
                <div className='item-name'>14:00</div>
                <div className='item-name'>12 Dec 2024</div>
            </div>
            <div className='item-row'>
                <div className='item-name'>103</div>
                <div className='item-name'>14:00</div>
                <div className='item-name'>16:00</div>
                <div className='item-name'>20 Dec 2024</div>
            </div>
        </div>
    );
};

export default Dashboard;
