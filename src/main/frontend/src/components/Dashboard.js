import React, {useEffect, useState} from 'react';
import { useNavigate } from 'react-router-dom';
import './Dashboard.css';
import axios from 'axios'

const Dashboard = () => {
    const navigate = useNavigate();
    const [teacher, setTeacher] = useState(null);
    const username = localStorage.getItem('username');
    const url = `api/private/teacher/get/${username}`;

    const handleLogout = () => {
        localStorage.removeItem('token'); // Remove token on logout
        localStorage.removeItem('username');
        navigate('/login'); // Redirect to login page
    };

    useEffect(() => {
        if (username) {
            axios.get(url, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`,
                },
                withCredentials: true
            })
                .then(response => {
                    setTeacher(response.data); // Store the fetched data in state
                })
                .catch(error => {
                    console.error('Error fetching data:', error);
                });
        }
    }, [username]);


    return (
        <div className="dashboard-container">
            <h2>Welcome to the Dashboard!</h2>
            <p>This is a protected page accessible only to authenticated users.</p>

            {teacher ? (
                <div>
                    <h3>Teacher Information:</h3>
                    <p>Username: {teacher.teacherId}</p>
                    <p>Name: {teacher.name}</p>
                    <p>Email: {teacher.email}</p>
                    <p>Department: {teacher.department}</p>
                </div>
            ) : (
                <p>Loading data...</p>
            )}

            <button onClick={handleLogout} className="logout-button">Logout</button>
        </div>
    );
};

export default Dashboard;
