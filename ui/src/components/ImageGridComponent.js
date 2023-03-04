const ImageGridComponent = () => {
    return(
        <div className="p-5 bg-white border-black border-2 border-r-0">
            <div className="relative">
              <img
                src="https://a.storyblok.com/f/165154/800x800/70e8f9cf61/sell-shirts.jpeg/m/filters:format(webp)"
                alt="new"
                className=""
              />
              <div className="overlay absolute opacity-70"></div>
              <div className="absolute inset-0 flex flex-col justify-end items-start p-5 text-white">
                <div className="flex flex-row gap-20">
                    <h1>
                    White T-Shirt
                    </h1>
                    <h1 className="">
                        75.00
                    </h1>
                </div>
                <h1>Explore</h1>
              </div>
            </div>
          </div>
    );
}

export default ImageGridComponent;