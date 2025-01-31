import { View, StyleSheet, Button } from 'react-native';
import { createPdf } from 'react-native-pdf-from-image';
import { launchImageLibrary } from 'react-native-image-picker';
import FileViewer from 'react-native-file-viewer';

export default function App() {
  return (
    <View style={styles.container}>
      <Button
        title="Launch Image Library"
        onPress={() => {
          launchImageLibrary(
            { mediaType: 'photo', selectionLimit: 6 },
            (response) => {
              console.log(response);
              const { filePath } = createPdf({
                imagePaths:
                  response?.assets?.map((asset) => asset.uri ?? '') || [],
                name: 'myPdf',
                paperSize: 'A4',
                customPaperSize: {
                  height: 300,
                  width: 300,
                },
              });
              console.log('pdf path', filePath);
              FileViewer.open(filePath);
            }
          );
        }}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});
