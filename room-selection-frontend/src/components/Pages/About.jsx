import React from 'react';
import './About.css';

const About = () => {
    return (
        <div className="about-us-container">
            <h1 className="about-us-title">About Us</h1>
            <p className="about-us-text">
                TeachSpace is an innovative web-based application designed to streamline the classroom and facility booking process. Our platform aims to enhance the efficiency and convenience of reserving rooms for various academic purposes such as lectures, seminars, and workshops.
            </p>
            <p className="about-us-text">
                By automating and centralizing the booking process, we aim to save professors and faculty members valuable time, enabling them to focus on what matters most—teaching and research. TeachSpace makes it easy to find and book available spaces in real time, without the hassle of manual coordination or paperwork.
            </p>
            <p className="about-us-text">
                Whether you're a professor looking for a quiet room for office hours or a department head organizing a conference, TeachSpace provides a seamless solution that ensures availability, convenience, and peace of mind.
            </p>

            <div className="contact-us-section">
                <h2>Contact Us</h2>
                <p>If you have any questions or need support, feel free to reach out to us:</p>
                <div className="contact-info">
                    <p><strong>Email:</strong> support@teachspace.hu</p>
                    <p><strong>Phone:</strong> +36 (1) 234-5678</p>
                    <p><strong>Address:</strong> 1234 Oktatás utca, 567. épület, Budapest, Hungary</p>
                </div>
            </div>
        </div>
    );
}

export default About;
