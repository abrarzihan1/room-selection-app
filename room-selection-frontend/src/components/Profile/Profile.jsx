import React, { useEffect, useState } from 'react';
import axios from "axios";
import Sidebar from "../Sidebar/Sidebar";
import "./Profile.css";

function Profile() {
    const [teacher, setTeacher] = useState(null);
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(true);

    const username = localStorage.getItem('username');
    const url = `api/private/teacher/get/${username}`;

    useEffect(() => {
        if (username) {
            axios.get(url, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`,
                },
                withCredentials: true
            })
                .then(response => {
                    setTeacher(response.data);
                    setLoading(false);
                })
                .catch(error => {
                    setError('Error fetching data. Please try again later.');
                    setLoading(false);
                });
        } else {
            setError('No username found.');
            setLoading(false);
        }
    }, [url, username]);

    return (
        <div className="dashboard-container">
            <Sidebar />
            <div className="dashboard-content">
                <h1>Teacher Information</h1>
                {loading ? (
                    <p>Loading data...</p>
                ) : error ? (
                    <p className="error-message">{error}</p>
                ) : teacher ? (
                    <div>
                        <div className={"profile-row"}><strong>Username</strong> {teacher.teacherId}</div>
                        <div className={"profile-row"}><strong>Name</strong> {teacher.name}</div>
                        <div className={"profile-row"}><strong>Email</strong> {teacher.email}</div>
                        <div className={"profile-row"}><strong>Department</strong> {teacher.department}</div>
                    </div>
                ) : (
                    <p>No teacher data found.</p>
                )}
            </div>
        </div>
    );
}

export default Profile;
