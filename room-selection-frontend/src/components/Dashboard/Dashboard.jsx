import React from 'react';
import './Dashboard.css';
import Sidebar from '../Sidebar/Sidebar';


const Dashboard = () => {
  return (
    <div className='dashboard'>
      <h2>Welcome to the Dashboard!</h2>
      <p>This is a protected page accessible only to authenticated users.</p>
      
    </div>  
  )
};

export default Dashboard;
