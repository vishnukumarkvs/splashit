import React from "react";
import BottomFeed from "../components/BottomFeed";
import CollectionsWear from "../components/CollectionsWear";
import Navbar from "../components/Navbar";

function Home(){
    return (
        <div className="bg-[#feee80] h-full">
            <Navbar/>
            <CollectionsWear/>
            <BottomFeed/>
        </div>
    );
}

export default Home;