import { useState } from 'react';
import BookingStart from './BookingStart';
import BookingRoomCriteria from './BookingRoomCriteria';
import BookingDateTime from './BookingDateTime';
import Summary from './Summary';
import Sidebar from '../Sidebar/Sidebar';
import './Booking.css';

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
                return <BookingDateTime formData={formData} handleChange={handleChange} nextStep={nextStep} prevStep={prevStep} />;
            case 4:
                return <Summary formData={formData} prevStep={prevStep} />;
            default:
                return <div>Error: Step not found</div>;
        }
    };

    return (
        <div className="booking-container">
            <Sidebar />
            <div className="booking-content">{renderStep()}</div>
        </div>
    );
}

export default Booking;
