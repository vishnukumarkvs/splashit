import {BsArrowUpRight} from 'react-icons/bs'

const CollectionsWear = () =>{
    return(
        <div>
            <div className="flex font-archivo m-5 mx-20 justify-around text-[8rem]">
                <h1>EYE</h1>
                <h1 className="italic">OF</h1>
                <h1>THE</h1>
                <h1>DAY</h1>
            </div>
            <div className="flex justify-center p-4">
                <img 
                    src="https://media.istockphoto.com/id/1063940578/vector/vector-illustration-of-owl-on-the-dark-background.jpg?s=612x612&w=0&k=20&c=2bhHkc4dBUHp7uc_-Ny9bp4iYM24971iOto6cp1YxZk="
                    alt="new"
                />
            </div>
            <div className="flex justify-evenly p-5 mt-10 items-center">
                <p className="font-bree font-bold text-5xl"> Featured <br/> Collections </p>
                <p>Our company was built by trust <br/> and reputation. we know how to<br/> make our customers love us</p>
                <a href="#_" class="relative inline-block px-4 py-2 font-medium group">
                    <span class="absolute inset-0 w-full h-full transition duration-200 ease-out transform translate-x-1 translate-y-1 bg-black group-hover:-translate-x-0 group-hover:-translate-y-0"></span>
                    <span class="absolute inset-0 w-full h-full bg-white border-2 border-black group-hover:bg-black"></span>
                    <span class="relative text-black group-hover:text-white">View All <BsArrowUpRight/></span>
                </a>

            </div>
        </div>
    );
}

export default CollectionsWear;