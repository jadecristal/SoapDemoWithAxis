package gov.weather;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

import static org.junit.Assert.*;

/**
 * @author cwinebrenner
 */
public class NdfdXMLBindingStubTest  {

    private final Logger log = Logger.getLogger(this.getClass());

    @Ignore("Only used to find out what the XML that comes back looks like to reverse-engineer a schema")
    public void latLonListZipCodeTest() throws Exception  {
        NdfdXMLBindingStub binding = (NdfdXMLBindingStub) new NdfdXMLLocator().getndfdXMLPort();
        String result = binding.latLonListZipCode("53705");
        assertEquals("Result did not match expected value", "???", result);
    }

    @Test
    public void latLonListZipCodeJAXBTest() throws Exception  {
        JAXBContext jaxbContext = JAXBContext.newInstance(DwmlType.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        NdfdXMLBindingStub binding = (NdfdXMLBindingStub) new NdfdXMLLocator().getndfdXMLPort();
        String result = binding.latLonListZipCode("53711");

        DwmlType dwml = (DwmlType)jaxbUnmarshaller.unmarshal(new StringReader(result));
        log.info(dwml.getLatLonList());
        assertTrue("DwmlType dwml is null; unmarshalling failed!", dwml != null);
    }
}
