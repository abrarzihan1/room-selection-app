import {useState} from 'react';
import BookingStart from "./BookingStart";
import BookingRoomCriteria from "./BookingRoomCriteria";
import BookingDateTime from "./BookingDateTime";
import Summary from "./Summary";

function Booking() {
    const [bookingDetails, setBookingDetails] = useState({
        name: '',
        roomId: null,
        date: new Date(),
        time: ''
    });

    const [formData, setFormData] = useState({
        roomType: '',
        capacity: 0,
        date: new Date(),
        hasComputers: false,
        hasProjectors: false,
        hasWhiteBoard: false
    });

    const [currentStep, setCurrentStep] = useState(1);

    const nextStep = () => {
        setCurrentStep(prevStep => prevStep + 1);
    };

    const prevStep = () => {
        setCurrentStep(prevStep => prevStep - 1);
    };

    const handleChange = (key, value) => {
        setBookingDetails(prevDetails => ({
            ...prevDetails,
            [key]: value,
        }));
    };

    const renderStep = () => {
        switch (currentStep) {
            case 1:
                return <BookingStart bookingDetails={bookingDetails} handleChange={handleChange} nextStep={nextStep} />;
            case 2:
                return <BookingRoomCriteria formData={formData} handleChange={handleChange} nextStep={nextStep} prevStep={prevStep} />;
            case 3:
                return <BookingDateTime bookingDetails={bookingDetails} handleChange={handleChange} nextStep={nextStep} prevStep={prevStep} />;
            case 4:
                return <Summary bookingDetails={bookingDetails} prevStep={prevStep} />;
            // case 5:
            //     return <DateTime bookingDetails={bookingDetails} handleChange={handleChange} nextStep={nextStep} prevStep={prevStep} />;
            // case 6:
            //     return <Summary bookingDetails={bookingDetails} prevStep={prevStep} />;
            default:
                return <div>Error: Step not found</div>;
        }
    };

    return (
        <div>{renderStep()}</div>
    );
}

export default Booking;