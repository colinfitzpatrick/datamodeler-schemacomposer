package com.xsynergy.schemacomposer;

import java.io.File;

import java.io.FileWriter;

import java.io.IOException;

import javax.swing.JFileChooser;

public class XSD
{

  
  public XSD()
  {
    super();
  }
  
  public void generate(String name)
  {
    String outputFile = "";

    final JFileChooser fc = new JFileChooser();

    fc.setDialogTitle("Choose Directory");
    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

    int returnVal = fc.showOpenDialog(null);
    if (returnVal == JFileChooser.APPROVE_OPTION)
    {
      File file = fc.getSelectedFile();
      outputFile = file.getAbsolutePath();
    }
    else
    {

    }

    try
    {
      FileWriter writer = new FileWriter(outputFile + File.separator + name + ".xsd", false);
      writer.write(file);
      writer.close();
    }
    catch (IOException e)
    {
      e.toString();
    }

  }
  
  String file = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" + 
  "<xs:schema xmlns:jlebo=\"http://soa.johnlewispartnership.com/schema/ebo/carrierlabel/v1\" targetNamespace=\"http://soa.johnlewispartnership.com/schema/ebo/carrierlabel/v1\" version=\"20160120\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">\n" + 
  "  <xs:element name=\"CarrierLabel\" type=\"jlebo:CarrierLabel\" />\n" + 
  "  <xs:complexType name=\"Carrier\">\n" + 
  "    <xs:annotation>\n" + 
  "      <xs:documentation>An external party that transports merchandise and/or supply items from their source to the retail store and from the retail \n" + 
  "store back to their source.</xs:documentation>\n" + 
  "    </xs:annotation>\n" + 
  "    <xs:sequence>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"VendorID\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation />\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "    </xs:sequence>\n" + 
  "  </xs:complexType>\n" + 
  "  <xs:complexType name=\"CarrierLabel\">\n" + 
  "    <xs:annotation>\n" + 
  "      <xs:documentation>The Carrier Label which may be used on a parcel for delivery.\n" + 
  "\n" + 
  "As of Nov 2015, Metapack provide the Carrier Label.  The Carrier label is provided in ZPL format and encoded as BASE64 over a SOAP web service.</xs:documentation>\n" + 
  "    </xs:annotation>\n" + 
  "    <xs:sequence>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Carrier\" type=\"jlebo:Carrier\" />\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"CarrierLabel\" type=\"xs:base64Binary\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The Carrier supplied label which is affixed to the Parcel</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"CarrierLabelID\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>A unique system assigned identifier for the Carrier Label</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"CarrierShipmentType\" type=\"jlebo:CarrierShipmentType\" />\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"FileFormatType\" type=\"jlebo:FileFormatType\" />\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ParcelForDelivery\" type=\"jlebo:ParcelForDelivery\" />\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"TrackingID\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>A tracking number provided by the service provider who actually delivers the shipment.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"TrackingURL\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>A URL to the service provider's website, which allows interested parties to track the shipment.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "    </xs:sequence>\n" + 
  "  </xs:complexType>\n" + 
  "  <xs:complexType name=\"CarrierShipmentType\">\n" + 
  "    <xs:annotation>\n" + 
  "      <xs:documentation>A unique, retailer supplied identifier for the carrier assigned to the Retail Transaction Shipment.</xs:documentation>\n" + 
  "    </xs:annotation>\n" + 
  "    <xs:sequence>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"CarrierShipmentTypeID\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>A unique, retailer supplied identifier for the carrier assigned to the Retail Transaction Shipment.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"CarrierShipmentTypeName\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>A unique, carrier supplied identifier for the shipment type.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "    </xs:sequence>\n" + 
  "  </xs:complexType>\n" + 
  "  <xs:complexType name=\"City\">\n" + 
  "    <xs:annotation>\n" + 
  "      <xs:documentation>The City of the address</xs:documentation>\n" + 
  "    </xs:annotation>\n" + 
  "    <xs:sequence>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"CityCode\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The code of the city</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"CityID\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>Unique City ID</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"CityName\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The name of the city</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "    </xs:sequence>\n" + 
  "  </xs:complexType>\n" + 
  "  <xs:complexType name=\"Dimensions\">\n" + 
  "    <xs:annotation>\n" + 
  "      <xs:documentation>The measurable extent of a physical item , such as length, depth, or height.</xs:documentation>\n" + 
  "    </xs:annotation>\n" + 
  "    <xs:sequence>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Depth\" type=\"xs:decimal\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation />\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"DimensionID\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation />\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Height\" type=\"xs:decimal\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation />\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Length\" type=\"xs:decimal\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation />\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"LinearUnitOfMeasureCode\" type=\"jlebo:UnitOfMeasure\" />\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Volume\" type=\"xs:decimal\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation />\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"VolumeUnitOfMeasureCode\" type=\"jlebo:UnitOfMeasure\" />\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Weight\" type=\"xs:decimal\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation />\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"WeightUnitOfMeasureCode\" type=\"jlebo:UnitOfMeasure\" />\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Width\" type=\"xs:decimal\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation />\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "    </xs:sequence>\n" + 
  "  </xs:complexType>\n" + 
  "  <xs:complexType name=\"EmailAddress\">\n" + 
  "    <xs:annotation>\n" + 
  "      <xs:documentation>An electronic mail address.</xs:documentation>\n" + 
  "    </xs:annotation>\n" + 
  "    <xs:sequence>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"AlternatePartyContactMethodID\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>An 'alternate', less cumbersome, unique identifier for this entity</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"CompleteEmail\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The complete/composite email address  e.g. 'Freddy.Smith@JohnLewis.com'.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"EffectiveDateTime\" type=\"xs:dateTime\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The date from which the contact method is applicable.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"EmailAddressDomainPart\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The domain portion identifies the point to which the mail is delivered.  In the dot-atom form, this is interpreted as an Internet domain name (either a host name or a mail exchanger name) as described in [RFC1034], [RFC1035], and [RFC1123].  In the domain-literal form, the domain is interpreted as the literal Internet address of the particular host.  In both cases, how addressing is used and how messages are transported to a particular host is covered in separate documents, such as [RFC5321].</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"EmailAddressID\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>A unique system assigned identifier for an electronic address for sending messages.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"EmailAddressLocalPart\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The local-part portion is a domain-dependent string.  In addresses, it is simply interpreted on the particular host as a name of a  particular mailbox.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ExpirationDateTime\" type=\"xs:dateTime\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The date until which the contact method is applicable.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"PrioritySequenceNo\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>If more than one PartyContactMethods exist (for all of the main PK combinations) then this dictates the priority sequence that should be used in the defined circumstances.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "    </xs:sequence>\n" + 
  "  </xs:complexType>\n" + 
  "  <xs:complexType name=\"FileFormatType\">\n" + 
  "    <xs:annotation>\n" + 
  "      <xs:documentation>A reference entity type that lists valid kinds of file formats that may be associated with DigitalAssets and DigitalItems.Examples includeMPEG,JPEG,FLASH,etc.</xs:documentation>\n" + 
  "    </xs:annotation>\n" + 
  "    <xs:sequence>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"FileFormatExtension\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>File extension typically used to designate a file as this type of file format.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"FileFormatName\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>Business name for the FileFormatType</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"FileFormatTypeCode\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>Identifies a digitial file format.Exmples include:mpegjpegQuickTimeflashvideo</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "    </xs:sequence>\n" + 
  "  </xs:complexType>\n" + 
  "  <xs:complexType name=\"ISO3166-1Country\">\n" + 
  "    <xs:annotation>\n" + 
  "      <xs:documentation>Locality in which the retailer does business. Provides a mapping from ISO-3166-1 two-character codes to country names. Provides a mapping from ISO-3166-1 two-character codes to the ITU assigned telephone number dialling code.</xs:documentation>\n" + 
  "    </xs:annotation>\n" + 
  "    <xs:sequence>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"CountryName\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The name of a locality in which the retailer does business.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ISO3166FourCharacterCountryCode\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>ISO designated four character abbreviation for a country, Also known as 'Formerly used codes' this four-letter country code represents a country name that is no longer in use. The structure depends on the reason why the country name was removed from ISO 3166-1 and added to ISO 3166-3.\n" + 
  "</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ISO3166ThreeCharacterCountryCode\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>ISO designated three character abbreviation for a country,</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ISO3166ThreeNumberCountryCode\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>ISO designated three\n" + 
  "digit abbreviation/\n" + 
  "codification for a\n" + 
  "country</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ISOCountryCode\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The ISO-3166-1 two letter code denoting a locality in which the retailer does business.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"PrimaryCountryFlag\" type=\"xs:boolean\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>Indicates whether the country is the primary country for the item\\supplier. Valid boolean values are Y(=1) and N(=0).</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"UserDefinedCodeFlag\" type=\"xs:boolean\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>A Boolean flag to\n" + 
  "indicate if this is a user\n" + 
  "(JL) defined country\n" + 
  "codification.\n" + 
  "Yes (=1), No (=0). 'No'\n" + 
  "will be the default.\n" + 
  "Specific ISO codes are\n" + 
  "set aside by the ISO for\n" + 
  "this express purpose.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "    </xs:sequence>\n" + 
  "  </xs:complexType>\n" + 
  "  <xs:complexType name=\"ISO4217-CurrencyType\">\n" + 
  "    <xs:annotation>\n" + 
  "      <xs:documentation />\n" + 
  "    </xs:annotation>\n" + 
  "    <xs:sequence>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"CostNODecimals\" type=\"xs:decimal\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>Contains the number of decimals supported by the currency for costs (buying perspective).</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ISOCurrencyCode\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>Currency code designated by ISO to identify national currency</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ISOCurrencyName\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>ISO designated currency name</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ISOCurrencyNumber\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>Numeric code assigned by ISO to identify national currencies</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"RetailNODecimals\" type=\"xs:decimal\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>Contains the number of decimals (allowed after the point) supported by the currency for retail selling.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"RetailerAssignedCurrencyTypeCode\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>Retailer assigned currency code that may be used as an alternate key.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Symbol\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>National symbol for the main unit of currency, e.g. £, $, etc</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "    </xs:sequence>\n" + 
  "  </xs:complexType>\n" + 
  "  <xs:complexType name=\"ParcelForDelivery\">\n" + 
  "    <xs:annotation>\n" + 
  "      <xs:documentation>An entity that holds relevant details for a single parcel that has been planned for delivery to a customer.</xs:documentation>\n" + 
  "    </xs:annotation>\n" + 
  "    <xs:sequence>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Dimensions\" type=\"jlebo:Dimensions\" />\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ISO4217-CurrencyType\" type=\"jlebo:ISO4217-CurrencyType\" />\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ParcelBarCode\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation />\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"unbounded\" name=\"ParcelForDeliveryShipmentItem\" type=\"jlebo:ParcelForDeliveryShipmentItem\" />\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ParcelID\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation />\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "    </xs:sequence>\n" + 
  "  </xs:complexType>\n" + 
  "  <xs:complexType name=\"ParcelForDeliveryShipmentItem\">\n" + 
  "    <xs:annotation>\n" + 
  "      <xs:documentation> </xs:documentation>\n" + 
  "    </xs:annotation>\n" + 
  "    <xs:sequence>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"RetailTransactionShipmentItem\" type=\"jlebo:RetailTransactionShipmentItem\" />\n" + 
  "    </xs:sequence>\n" + 
  "  </xs:complexType>\n" + 
  "  <xs:complexType name=\"PostalAddress\">\n" + 
  "    <xs:annotation>\n" + 
  "      <xs:documentation>An ordinary postal address for the Party or Site.</xs:documentation>\n" + 
  "    </xs:annotation>\n" + 
  "    <xs:sequence>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"AddressLine1\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The first line of the address, normally the street number and name.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"AddressLine2\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The second line of an address, normally the Flat or Building Suite number.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"AddressLine3\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The third line of the address.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"AddressLine4\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The fourth line of the address.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"AddressLine5\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The fifth line of the address.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"AlternatePartyContactMethodID\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>An 'alternate', less cumbersome, unique identifier for this entity</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"City\" type=\"jlebo:City\" />\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"EffectiveDateTime\" type=\"xs:dateTime\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The date from which the contact method is applicable.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ExpirationDateTime\" type=\"xs:dateTime\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The date until which the contact method is applicable.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"HouseName\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>A specific house name for the Address</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"HouseNo\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The house number for the Address</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ISO3166-1Country\" type=\"jlebo:ISO3166-1Country\" />\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"PostalAddressID\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>A unique system allocated identifier for the Address.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"PostalCodeReference\" type=\"jlebo:PostalCodeReference\" />\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"PrioritySequenceNo\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>If more than one PartyContactMethods exist (for all of the main PK combinations) then this dictates the priority sequence that should be used in the defined circumstances.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Territory\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The County, State, Province, Territory etc component of the address</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"UpdateTimestamp\" type=\"xs:dateTime\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The date and the time that the record of the Address was updated ( i.e. created or subsequently amended).</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "    </xs:sequence>\n" + 
  "  </xs:complexType>\n" + 
  "  <xs:complexType name=\"PostalCodeReference\">\n" + 
  "    <xs:annotation>\n" + 
  "      <xs:documentation>&lt;b&gt;Identifies and defines all postal codes for given country. In the US this is ZIP CODE. This entity is intended to support a variety of postal code formats and value lists for countries that use them (which is US, Canada, Brazil, Western Europe, Japan, etc.&lt;/b&gt;</xs:documentation>\n" + 
  "    </xs:annotation>\n" + 
  "    <xs:sequence>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"PostalCode\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>Postal code value. In this is an alphanumeric attribute because unlike the US other countries use letters in their postal codes.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"PostalCodeDescription\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>PostalCode name or descriptioin</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"PostalCodeExtension\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>This is a second tier postal code that qualifies the PostalCode attribute and allows more precise mail sorting. It does not apply to all countries' postal codes. Also, it is usually not mandatory. So it can be omitted for business purposes. For data integrity purposes this attribute will be assigned a default values of all zeros so it can be treated as part of an unique alternate key.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"PostalCodeID\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>A token identifier for a generic postal code. Postal codes vary in format, structure and content from one country to another. This generic ID ensures that each postal code is unique across ALL COUNTRIES.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "    </xs:sequence>\n" + 
  "  </xs:complexType>\n" + 
  "  <xs:complexType name=\"RetailTransactionShipment\">\n" + 
  "    <xs:annotation>\n" + 
  "      <xs:documentation>The record of a shipment which was made to deliver the goods\n" + 
  "purchased in a particular RetailTransaction to the customer's\n" + 
  "shipping address(es)</xs:documentation>\n" + 
  "    </xs:annotation>\n" + 
  "    <xs:sequence>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ActualReceivedDateTime\" type=\"xs:dateTime\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The actual date &amp;amp; time the shipment was received at the shipment address.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ActualShipDateTime\" type=\"xs:dateTime\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The preferred delivery date &amp;amp; time</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ConsignmentDateTime\" type=\"xs:dateTime\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The consignment date for the items to be shipped</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"EmailAddress\" type=\"jlebo:EmailAddress\" />\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"EstimatedReceivedDateTime\" type=\"xs:dateTime\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The estimated date &amp;amp; time the shipment will be received at the shipment address.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"EveningTelephone\" type=\"jlebo:Telephone\" />\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"PaymentMethodCode\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>A code denoting which payment method was used when paying for a COD shipment</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"PostalAddress\" type=\"jlebo:PostalAddress\" />\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"RequiredPaymentAmount\" type=\"xs:decimal\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>How much is required to be paid for COD shipment?</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"RetailTransactionShipmentID\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>A unique, retailer supplied identifier for a RetailTransactionShipment.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"RetailTransactionShipmentStateCode\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>A code denoting the state of the Retail Transaction shipment.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ScheduledReceivedDateTime\" type=\"xs:dateTime\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The scheduled date &amp;amp; time the shipment will be received at the shipment address.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ServiceInformationFlag\" type=\"xs:boolean\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>A flag that indicates that at least one item in a shipment is of type service.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ShipToCompanyName\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The company name given by the customer</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ShipToPersonName\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The name of the party to whom the delivery is made.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"SourceSystemName\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>A code denoting the source system generating the retail transaction shipment event e.g.\n" + 
  "Kratzer, Mnetics</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"SpecialInstructions\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>Narrative text, giving special delivery instructions.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Telephone\" type=\"jlebo:Telephone\" />\n" + 
  "    </xs:sequence>\n" + 
  "  </xs:complexType>\n" + 
  "  <xs:complexType name=\"RetailTransactionShipmentItem\">\n" + 
  "    <xs:annotation>\n" + 
  "      <xs:documentation>A associative entity that relates a particular item sale to a the\n" + 
  "Shipment that was used to deliver the merchandise to the Customer's\n" + 
  "shipping address(es)</xs:documentation>\n" + 
  "    </xs:annotation>\n" + 
  "    <xs:sequence>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Quantity\" type=\"xs:decimal\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>Denotes how many of the items in the relevant SaleReturnLineItem are to be delivered in this shipment. The implication being that one may purchase more than one of an item in a single SaleReturnLineItem and each instance of the item purchased may be shipped to a different address.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"RetailTransactionShipment\" type=\"jlebo:RetailTransactionShipment\" />\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ShipmentLineItemSequenceNumber\" type=\"xs:decimal\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>A unique, nonsignificant, automatically assigned sequential number used to identify this item within the context of the RetailTransactionShipment.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "    </xs:sequence>\n" + 
  "  </xs:complexType>\n" + 
  "  <xs:complexType name=\"Telephone\">\n" + 
  "    <xs:annotation>\n" + 
  "      <xs:documentation>A telephone, cellular, pager, fax or other telecommunication device number.</xs:documentation>\n" + 
  "    </xs:annotation>\n" + 
  "    <xs:sequence>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"AlternatePartyContactMethodID\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>An 'alternate', less cumbersome, unique identifier for this entity</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"AreaCode\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The 1 - 6 digit area code that further categorizes the Telephone with it's Country.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"CompleteNumber\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The complete telephone number including the CountryCode, AreaCode, Telephone Number and ExtensionNumber.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"EffectiveDateTime\" type=\"xs:dateTime\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The date from which the contact method is applicable.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ExpirationDateTime\" type=\"xs:dateTime\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The date until which the contact method is applicable.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ExtensionNumber\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>Addtional numbers required to reach a Party's Telephone or voice mailbox.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"PrioritySequenceNo\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>If more than one PartyContactMethods exist (for all of the main PK combinations) then this dictates the priority sequence that should be used in the defined circumstances.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"TelephoneID\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>A unique system assigned identifier for this Telephone number</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"TelephoneNumber\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The 5 - 8 digit number for the Telephone within the confines of the ITUCountryCode and AreaCode.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "    </xs:sequence>\n" + 
  "  </xs:complexType>\n" + 
  "  <xs:complexType name=\"UnitOfMeasure\">\n" + 
  "    <xs:annotation>\n" + 
  "      <xs:documentation>Identifies and describes valid units of measure that are used throughout the model.</xs:documentation>\n" + 
  "    </xs:annotation>\n" + 
  "    <xs:sequence>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Description\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>Description assigned to this unit of measure.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"EnglishMetricFlag\" type=\"xs:boolean\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>Indicates whether this unit of measure is part of the English (i.e. 'Imperial') or Metric system of measurements.\n" + 
  "\n" + 
  "0 - English (i.e. 'Imperial', the default)\n" + 
  "1 - Metric</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Name\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>Name assigned to this unit of measure.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"UnitOfMeasureCode\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>The code used to specify the units in which a value is being expressed, or manner in which a measurement has been taken. This code relates to the UCC data element 355.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "      <xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"UnitOfMeasureTypeCode\" type=\"xs:string\">\n" + 
  "        <xs:annotation>\n" + 
  "          <xs:documentation>Indicates what this unit of measure entity type instance measures. Examples include weight or mass, length, cube (length x width x depth), volume, discrete items (each), etc.</xs:documentation>\n" + 
  "        </xs:annotation>\n" + 
  "      </xs:element>\n" + 
  "    </xs:sequence>\n" + 
  "  </xs:complexType>\n" + 
  "</xs:schema>";
}
