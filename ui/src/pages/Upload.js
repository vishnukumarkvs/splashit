import { useState } from "react";
import { useDropzone } from "react-dropzone";
import classNames from "classnames";

const Upload = () => {
  const [image, setImage] = useState(null);
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');

  const handleTitleChange = (event) => {
    setTitle(event.target.value);
  }

  const handleDescriptionChange = (event) => {
    setDescription(event.target.value);
  }
  const onDrop = (acceptedFiles) => {
    setImage(URL.createObjectURL(acceptedFiles[0]));
  };

  const { getRootProps, getInputProps, isDragActive } = useDropzone({
    onDrop,
    accept: "image/*",
    multiple: false,
  });

  return (
    <div className="bg-gradient-to-b from-blue-100 to-purple-100">
      <div className="w-[70%] h-screen mx-auto flex flex-col">
        <div className="m-5 mx-0">
          <p className="text-lg m-1 mx-0">Title</p>
          <input type="text" className="bg-transparent italic border-2 p-2 border-gray-400 rounded-md" value={title} onChange={handleTitleChange} />
          {/* {console.log(title)} */}
        </div>
        <div className="m-5 mx-0">
          <p className="text-lg m-1 mx-0">Description</p>
          <textarea
            rows="3"
            cols="50"
            spellCheck="false"
            className="border-2 italic bg-transparent border-gray-400 p-2 rounded-md"
            value={description}
            onChange={handleDescriptionChange}
          />
          {/* {console.log(description)} */}
        </div>
        <div className="m-5 mx-0">
          <p className="m-1 mx-0 text-lg">Add Image</p>
          <div
            {...getRootProps()}
            className={classNames(
              "border-dashed border-2 border-gray-400 rounded-lg p-4",
              {
                "bg-gray-100": isDragActive,
              }
            )}
          >
            <input {...getInputProps()} />
            {image ? (
              <img
                src={image}
                alt="Uploaded image"
                className="max-w-full h-auto"
              />
            ) : (
              <p className="text-center">
                Drag and drop an image or click to select a file
              </p>
            )}
          </div>
        </div>
        <div className="m-5 mx-0">
            <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 border border-blue-700 rounded m-5 mx-0">
                Submit
            </button>
        </div>
      </div>
    </div>
  );
};

export default Upload;
