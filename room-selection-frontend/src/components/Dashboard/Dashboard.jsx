import React, {useEffect, useState} from 'react';
import './Dashboard.css';
import Sidebar from "../Sidebar/Sidebar";
import axios from "axios";

const Dashboard = () => {
    const [bookings, setBookings] = useState([]);

    useEffect(() => {
        const fetchBookings = async () => {
            try {
                const response = await axios.get(
                    `api/public/booking/teacher/${localStorage.getItem('username')}`,
                );
                setBookings(response.data);
                // setLoading(false);  // Data has been fetched, set loading to false
            } catch (error) {
                console.error("Error fetching rooms. Please try again later.");
                // setLoading(false);  // In case of error, stop loading
            }
        };

        fetchBookings();
    }, []);

    return (
        <div className="dashboard-container">
            <Sidebar/>
            <div className={"dashboard-content"}>
                <h1>Dashboard</h1>
                <div className='title-row'>
                    <div className='title-name'>
                        Name
                    </div>
                    <div className='title-name'>
                        Room Id
                    </div>
                    <div className='title-name'>
                        Date
                    </div>
                    <div className='title-name'>
                        Start time
                    </div>
                </div>
                {
                    bookings.map((booking, index) => {
                        return (
                            <div className={'item-row'} key={index}>
                                <div className='item-name'>{booking.name}</div>
                                <div className='item-name'>{booking.roomId}</div>
                                <div className='item-name'>{booking.date}</div>
                                <div className='item-name'>{booking.startTime}</div>
                            </div>
                        );
                    })
                }
            </div>
        </div>
    );
};

export default Dashboard;