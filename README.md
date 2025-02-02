<h1 align="center">
  <img width="200" height="200" src="./IMG/logo.png"/><br/>
  React Native Pdf From Image Library
</h1>

Generate PDF documents from an array of images.

<p align="center">
  <a href="https://www.npmjs.com/package/react-native-pdf-from-image">
    <img alt="npm version" src="https://badge.fury.io/js/react-native-pdf-from-image.svg"/>
  </a>
  <a title='License' href="https://github.com/benjamineruvieru/react-native-pdf-from-image/blob/master/LICENSE" height="18">
    <img src='https://img.shields.io/badge/license-MIT-blue.svg' />
  </a>
  <a title='Tweet' href="https://twitter.com/intent/tweet?text=Check%20out%20this%20awesome%20React%20Native%20PDF%20from%20Image%20Library&url=https://github.com/benjamineruvieru/react-native-pdf-from-image&via=benjamin_eru&hashtags=react,reactnative,opensource,github,ux" height="18">
    <img src='https://img.shields.io/twitter/url/http/shields.io.svg?style=social' />
  </a>
</p>

## 🌟 Features

- **Image to PDF Conversion**: Effortlessly convert images into PDF documents.
- **Customizable Paper Sizes**: Choose from standard paper sizes or define custom dimensions for your PDFs.

## Old Architecture Support

~~react-native-pdf-from-image is a pure TurboModule, and **requires the new architecture to be enabled**.~~
~~- Work is ongoing to support the old architecture.~~

The library now supports both new and old architecture! 🎉🎉🎉

## 🚀 Installation

```sh
npm install react-native-pdf-from-image
```

or

```sh
yarn add react-native-pdf-from-image
```

```sh
cd ios && pod install
```

## 📖 Usage

Here's a basic example of how to use the library:

```js
import { createPdf } from 'react-native-pdf-from-image';

const images = ['path/to/image1.jpg'];

const { filePath } = createPdf({
  imagePaths: images,
  name: 'myPdf',
  paperSize: 'A4', // optional
  // optional
  customPaperSize: {
    height: 300,
    width: 300,
  },
});
```

> **Note:** When using the old architecture, make sure to await the `createPdf` function as it returns a Promise.

```js
import { createPdf } from 'react-native-pdf-from-image';

// Old Architecture
const generatePdf = async () => {
  const images = ['path/to/image1.jpg'];
  const { filePath } = await createPdf({
    imagePaths: images,
    name: 'myPdf',
    paperSize: 'A4', // optional
    // optional
    customPaperSize: {
      height: 300,
      width: 300,
    },
  });
};
```

## 📦 Props

### createPdf(params)

- params : An object containing the following properties:
  - imagePaths (Array of strings): An array of file paths to the images you want to include in the PDF.
  - name (string): The name of the PDF file to be created.
  - paperSize (string, optional): The size of the paper for the PDF. Common sizes like 'A4' are supported.
  - customPaperSize (object, optional): An object specifying custom dimensions for the paper size. It should have height and width properties.

### Returns

- An object containing:
  - filePath (string): The file path to the generated PDF document.

> **Note:** If neither paperSize or customPaperSize is passed then the image dimensions would be used as the paper size of the pdf.

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more information.

```

```
