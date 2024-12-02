import { useState } from 'react';
import BookingStart from './Start/BookingStart';
import BookingRoomCriteria from './RoomCriteria/BookingRoomCriteria';
import BookingDateTime from './DateTime/BookingDateTime';
import Summary from './Summary/Summary';
import Sidebar from '../Sidebar/Sidebar';
import BookingRoom from './Room/BookingRoom';
import './Booking.css';
import BookingResult from "./Result/BookingResult";

function Booking() {
    const [formData, setFormData] = useState({
        name: '',
        roomType: '',
        capacity: 0,
        hasComputers: false,
        hasProjectors: false,
        hasWhiteBoard: false,
        roomId: '',
        date: '',
        teacherId: '',
        startTime: '',
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
            case 6:
                return <BookingResult />
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
