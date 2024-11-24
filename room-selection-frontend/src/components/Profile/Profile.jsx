import React, {useEffect, useState} from 'react';
import axios from "axios";
import Sidebar from "../Sidebar/Sidebar";
import "./Profile.css"

function Profile() {
    const [teacher, setTeacher] = useState(null);
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
                    setTeacher(response.data); // Store the fetched data in state
                })
                .catch(error => {
                    console.error('Error fetching data:', error);
                });
        }
    },[url,username]);

    return (
        <div className={"profile-container"}>
            <Sidebar />
            <div className="profile-content">
                {teacher ? (
                    <div>
                        <h2>Teacher Information:</h2>
                        <p>Username: {teacher.teacherId}</p>
                        <p>Name: {teacher.name}</p>
                        <p>Email: {teacher.email}</p>
                        <p>Department: {teacher.department}</p>
                    </div>
                ) : (
                    <p>Loading data...</p>
                )}
            </div>
        </div>
    );
}

export default Profile;