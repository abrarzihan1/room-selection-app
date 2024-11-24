import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Login from './components/Login';
import Register from './components/Register';
import Dashboard from './components/Dashboard';
import BookingCalender from "./components/booking/BookingCalender";
import BookingForm from "./components/booking/BookingForm";
import BookingRoom from "./components/booking/BookingRoom";
import Booking from "./components/booking/Booking";

const App = () => {
    const [isAuthenticated, setIsAuthenticated] = useState(!!localStorage.getItem('token'));

    // Function to update authentication status after login
    const handleLoginSuccess = () => {
        setIsAuthenticated(true);
    };

    useEffect(() => {
        setIsAuthenticated(!!localStorage.getItem('token')); // Update when the component mounts
    }, []);

    return (
        <Router>
            <Routes>
                <Route
                    path="/"
                    element={isAuthenticated ? <Dashboard /> : <Navigate to="/login" />}
                />
                <Route path="/login" element={<Login onLoginSuccess={handleLoginSuccess} />} />
                <Route path="/signup" element={<Register />} />
                <Route
                    path="/dashboard"
                    element={isAuthenticated ? <Dashboard /> : <Navigate to="/login" />}
                />
                <Route path={"/booking"} element={<Booking />}/>
                <Route path="/booking/time" element={<BookingCalender />} />
                <Route path={"/booking/form"} element={<BookingForm />} />
                <Route path={"/booking/room"} element={<BookingRoom />} />
                <Route path="*" element={<Navigate to="/login" />} />
            </Routes>
        </Router>
    );
};

export default App;
