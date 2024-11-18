import React from 'react'
import Dashboard from "../Dashboard/Dashboard";
import Sidebar from "../Sidebar/Sidebar";
import "./AdminPage.css"

const AdminPage = () => {
    return (
        <div>
            <div className="admin-page">
                <Sidebar />
                <Dashboard />
            </div>
        </div>
    );
};

export default AdminPage