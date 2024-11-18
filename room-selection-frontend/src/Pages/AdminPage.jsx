import React from 'react'
import Dashboard from '../components/Dashboard/Dashboard'
import Sidebar from '../components/Sidebar/Sidebar'
import "./AdminPage.css"


const AdminPage = () => {
  return (
    <div className="admin-page">
      <Sidebar />
      <Dashboard />
    </div>
  );
};

export default AdminPage