import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Login.css';

const Login = ({ onLoginSuccess }) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        setError('');
        try {
            const response = await fetch('api/auth/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ username: username, password }),
                credentials: 'include', // Ensure cookies are sent
            });

            if (response.ok) {
                const data = await response.json();
                localStorage.setItem('token', data.token);
                localStorage.setItem('username', data.username);
                onLoginSuccess(); // Update authentication state in App.js
                navigate('/dashboard'); // Redirect to dashboard
            } else {
                const errorMessage = await response.text();
                setError(errorMessage || 'Failed to login');
            }
        } catch (err) {
            setError('An error occurred. Please try again.');
        }
    };



    return (
        <div className="login-container">
            <div className="login-box">
                <h2>TeachSpace</h2>
                <form onSubmit={handleLogin}>
                    <div className="input-container">
                        <label>Username</label>
                        <input
                            type="text"
                            placeholder="Enter your username"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                        />
                    </div>
                    <div className="input-container">
                        <label>Password</label>
                        <input
                            type="password"
                            placeholder="Enter your password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                    </div>
                    {error && <div className="error-message">{error}</div>}
                    <button type="submit" className="login-button">Login</button>
                </form>
                <div className="signup-link">
                    Don't have an account? <a href="/signup">Register here</a>
                </div>
            </div>
        </div>
    );
};

export default Login;
