import { useState } from 'react';
import BookingStart from './Start/BookingStart';
import BookingRoomCriteria from './RoomCriteria/BookingRoomCriteria';
import BookingDateTime from './BookingDateTime';
import Summary from './Summary/Summary';
import Sidebar from '../Sidebar/Sidebar';
import './Booking.css';
import BookingRoom from "./Room/BookingRoom";

function Booking() {
    const [formData, setFormData] = useState({
        name: '',
        roomType: '',
        capacity: 0,
        date: '',
        hasComputers: false,
        hasProjectors: false,
        hasWhiteBoard: false,
    });

    const [currentStep, setCurrentStep] = useState(1);

    const nextStep = () => setCurrentStep(prevStep => prevStep + 1);
    const prevStep = () => setCurrentStep(prevStep => prevStep - 1);

    const handleChange = (key, value) => {
        setFormData(prevData => ({
            ...prevData,
            [key]: value,
        }));
    };

    const renderStep = () => {
        switch (currentStep) {
            case 1:
                return <BookingStart formData={formData} handleChange={handleChange} nextStep={nextStep} />;
            case 2:
                return <BookingRoomCriteria formData={formData} handleChange={handleChange} nextStep={nextStep} prevStep={prevStep} />;
            case 3:
                return <BookingRoom formData={formData} handleChange={handleChange} nextStep={nextStep} prevStep={prevStep} />;
            case 4:
                return <BookingDateTime formData={formData} handleChange={handleChange} nextStep={nextStep} prevStep={prevStep} />;
            case 5:
                return <Summary formData={formData} prevStep={prevStep} />;
            default:
                return <div>Error: Step not found</div>;
        }
    };

    return (
        <div className="dashboard-container">
            <Sidebar />
            <div className="dashboard-content">{renderStep()}</div>
        </div>
    );
}

export default Booking;
