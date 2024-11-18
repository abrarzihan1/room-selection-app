import React, { useEffect, useState } from 'react';
import './Dashboard.css';


const Dashboard = () => {
  const [bookings, setBookings] = useState([]);

  // Fetch data from JSON file or API
  useEffect(() => {
    // Replace this fetch with actual API call if required
    fetch('/data.json') // Ensure this path is correct for your setup
      .then((response) => response.json())
      .then((data) => setBookings(data));
  }, []);

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
          {bookings.map((booking, index) => (
            <div className='item-row' key={index}>
              <div className='item-name'>{booking.roomId}</div>
              <div className='item-name'>{booking.startTime}</div>
              <div className='item-name'>{booking.endTime}</div>
              <div className='item-name'>{booking.date}</div>
            </div>
          ))}
    </div>
  );
};

export default Dashboard;
