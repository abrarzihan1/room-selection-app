import React, { useEffect, useState } from 'react';
import './Dashboard.css';
import Sidebar from "../Sidebar/Sidebar";
import axios from "axios";

const Dashboard = () => {
    const [bookings, setBookings] = useState([]);
    const [filteredBookings, setFilteredBookings] = useState([]);
    const [activeFilter, setActiveFilter] = useState('upcoming');

    useEffect(() => {
        const fetchBookings = async () => {
            try {
                const response = await axios.get(
                    `api/public/booking/teacher/${localStorage.getItem('username')}`,
                    {
                        headers: {
                            Authorization: `Bearer ${localStorage.getItem("token")}`,
                        },
                        withCredentials: true
                    }
                );
                const bookingsData = response.data;
                setBookings(bookingsData);
                setFilteredBookings(filterAndSortBookings(bookingsData, 'upcoming'));
            } catch (error) {
                console.error("Error fetching bookings. Please try again later.");
            }
        };

        fetchBookings();
    }, []);

    const filterAndSortBookings = (bookingsData, filter) => {
        const now = new Date();
        let filteredData;

        if (filter === 'upcoming') {
            filteredData = bookingsData.filter(booking => new Date(booking.date + ' ' + booking.startTime) > now);
            filteredData.sort((a, b) => {
                const dateA = new Date(a.date + ' ' + a.startTime);
                const dateB = new Date(b.date + ' ' + b.startTime);
                return dateA - dateB;
            });
        } else {
            filteredData = bookingsData.filter(booking => new Date(booking.date + ' ' + booking.startTime) <= now);
            filteredData.sort((a, b) => {
                const dateA = new Date(a.date + ' ' + a.startTime);
                const dateB = new Date(b.date + ' ' + b.startTime);
                return dateB - dateA;
            });
        }

        return filteredData;
    };

    const handleFilterChange = (filter) => {
        setActiveFilter(filter);
        setFilteredBookings(filterAndSortBookings(bookings, filter));
    };

    const handleDelete = async (bookingId) => {
        try {
            const response = await axios.delete(`/api/public/booking/booking/${bookingId}`, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`,
                },
                withCredentials: true
            });

            if (response.status === 200) {
                alert('Booking deleted successfully');
                // Remove the deleted booking from the filteredBookings and bookings state
                const updatedBookings = bookings.filter(booking => booking.bookingId !== bookingId);
                setBookings(updatedBookings);
                setFilteredBookings(filterAndSortBookings(updatedBookings, activeFilter)); // Re-filter after deletion
            } else {
                alert('Error deleting booking');
            }
        } catch (error) {
            alert('Error deleting booking');
        }
    };

    return (
        <div className="dashboard-container">
            <Sidebar />
            <div className="dashboard-content">
                <h1>Dashboard</h1>

                <div className="filter-buttons">
                    <button
                        onClick={() => handleFilterChange('upcoming')}
                        className={activeFilter === 'upcoming' ? 'active' : ''}
                    >
                        Upcoming Bookings
                    </button>
                    <button
                        onClick={() => handleFilterChange('past')}
                        className={activeFilter === 'past' ? 'active' : ''}
                    >
                        Past Bookings
                    </button>
                </div>

                <div className="title-row">
                    <div className="title-name">Name</div>
                    <div className="title-name">Room Id</div>
                    <div className="title-name">Date</div>
                    <div className="title-name">Start Time</div>
                    {activeFilter === 'upcoming' && <div className="title-name">Actions</div>}
                </div>

                {filteredBookings.map((booking, index) => {
                    return (
                        <div className="item-row" key={index}>
                            <div className="item-name">{booking.name}</div>
                            <div className="item-name">{booking.roomId}</div>
                            <div className="item-name">{booking.date}</div>
                            <div className="item-name">{booking.startTime}</div>
                            {activeFilter === 'upcoming' && (
                                <div className="item-name">
                                    <button
                                        onClick={() => handleDelete(booking.bookingId)}
                                        className="delete-button"
                                    >
                                        Delete
                                    </button>
                                </div>
                            )}
                        </div>
                    );
                })}
            </div>
        </div>
    );
};

export default Dashboard;
