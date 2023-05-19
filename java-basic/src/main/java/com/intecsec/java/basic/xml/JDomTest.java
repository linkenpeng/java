package com.intecsec.java.basic.xml;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * jdom
 * dom4j
 */
public class JDomTest {

	public static void main(String[] args) {
		System.out.println(genTicketXML());
	}

	public static String genTicketXML() {
		Element edgePOSTask = new Element("EdgePOSTask");
		Namespace namespace = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		edgePOSTask.addNamespaceDeclaration(namespace);
		edgePOSTask.setAttribute("noNamespaceSchemaLocation", "ticket.xsd");

		Element ticket = new Element("Ticket");
		ticket.setAttribute("Type", "Request");
		ticket.setAttribute("Action", "");
		ticket.setAttribute("Language", "EN");
		ticket.setAttribute("Time", "2017-11-22 14:37:01");
		ticket.setAttribute("Description", "LoyaltyModule");

		Element props = new Element("Props");
		Element prop = new Element("Prop").setAttribute("Key","").setAttribute("Value","");
		props.addContent(prop);
		ticket.addContent(props);

		Element print = new Element("Print");
		print.setAttribute("TxnId", "2017");
		print.setAttribute("TxnDate", "2017/11/22");
		print.setAttribute("TxnTime", "14:37:01");
		print.setAttribute("TicketId", "2017");
		print.setAttribute("Amount", "1.0");
		print.setAttribute("Quanlity", "1");
		print.setAttribute("Promotion", "0.0");
		print.setAttribute("Discount", "0");
		print.setAttribute("GUID", "");
		print.setAttribute("Istrainning", "False");
		print.setAttribute("RefundTicket", "0");
		print.setAttribute("RecallTicket", "0");
		print.setAttribute("SaveTicket", "0");
		print.setAttribute("VoidTicket", "0");
		print.setAttribute("Status", "1");
		print.setAttribute("SecurityCode", "ScanGo");
		print.setAttribute("IsBalance", "0");
		print.setAttribute("IsVoided", "0");
		print.setAttribute("IsRefund", "0");
		print.setAttribute("Remark", "");
		print.setAttribute("IsExport", "0");
		print.setAttribute("SupervisorID", "");
		print.setAttribute("SupervisorName", "");
		print.setAttribute("KeytotalAmount", "1.0");
		print.setAttribute("SchemeNo", "3");
		print.setAttribute("LoyaltyInfoXml", "");
		print.setAttribute("TargetMessage", "");
		print.setAttribute("PageTotalNo", "1");
		print.setAttribute("ReprintCount", "0");
		print.setAttribute("PageStart", "");
		print.setAttribute("PageEnd", "");
		print.setAttribute("IsReady", "");
		print.setAttribute("PageStart", "true");
		print.setAttribute("VATAmount", "1.0");
		print.setAttribute("Additional", "测试不需支付无流水号");

		Element casher = new Element("Casher");
		casher.setAttribute("Code", "1122");
		casher.setAttribute("Name", "SNG");
		casher.setAttribute("Telephone", "");
		casher.setAttribute("Remark", "");
		print.addContent(casher);

		Element customer = new Element("Customer");
		customer.setAttribute("Code", "");
		customer.setAttribute("Name", "");
		customer.setAttribute("Telephone", "");
		customer.setAttribute("Membercard", "");
		customer.setAttribute("Sex", "");
		customer.setAttribute("FirstName", "");
		customer.setAttribute("LastName", "");
		customer.setAttribute("Age", "");
		customer.setAttribute("DateOfBirth", "");
		print.addContent(customer);

		Element store = new Element("Store");
		store.setAttribute("Code", "0046");
		store.setAttribute("Description", "保护环境,乐用环保购物袋^顾客服务热线");
		store.setAttribute("Address", "深圳市福田区福华路");
		store.setAttribute("Telephone", "124356");
		store.setAttribute("Postcode", "");
		print.addContent(store);

		Element items = new Element("Items");

		Element item = new Element("Item");
		item.setAttribute("Type", "LineItem");
		item.setAttribute("Code", "12356987");
		item.setAttribute("Description", "清润舒缓喷雾50毫升");
		item.setAttribute("Price", "1.0");
		item.setAttribute("Quanlity", "1");
		item.setAttribute("Amount", "1.0");
		item.setAttribute("Barcode", "6933636805692");
		item.setAttribute("Voided", "0");
		item.setAttribute("Subtract", "0");
		item.setAttribute("Wasvoided", "0");
		item.setAttribute("IsTax", "1");
		item.setAttribute("Department", "");
		item.setAttribute("SupervisorID", "");
		item.setAttribute("SupervisorName", "");
		item.setAttribute("IconChar", "");
		item.setAttribute("SalesPersonID", "0");
		item.setAttribute("SalesPersonName", "0");
		item.setAttribute("ItemMessage", "");
		items.addContent(item);
		Element item2 = new Element("Item");
		item2.setAttribute("Type","Promotion");
		items.addContent(item2);
		print.addContent(items);

		Element tenders = new Element("Tenders");
		Element tender = new Element("Tender");
		tender.setAttribute("Type", "Tender");
		tender.setAttribute("Code", "73");
		tender.setAttribute("Description", "微信在线");
		tender.setAttribute("Amount", "1.0");
		tender.setAttribute("VoidedTender", "");
		tenders.addContent(tender);

		tender = new Element("Tender");
		tender.setAttribute("Type", "Cash");
		tender.setAttribute("Code", "2");
		tender.setAttribute("Description", "免收");
		tender.setAttribute("Amount", "0");
		tender.setAttribute("IsChanged", "False");
		tender.setAttribute("VoidedTender", "");
		tenders.addContent(tender);

		tender = new Element("Tender");
		tender.setAttribute("Type", "Cash");
		tender.setAttribute("Code", "");
		tender.setAttribute("Description", "找赎");
		tender.setAttribute("Amount", "0");
		tender.setAttribute("IsChanged", "True");
		tender.setAttribute("VoidedTender", "");
		tenders.addContent(tender);
		print.addContent(tenders);

		Element invoice = new Element("Invoice");
		invoice.setAttribute("TransType", "1");
		invoice.setAttribute("InvoiceCode", "");
		invoice.setAttribute("InvoiceNo", "");
		invoice.setAttribute("InvoiceEncode", "");
		invoice.setAttribute("RegisterNo", "31010160");
		invoice.setAttribute("PaymentUnit", "个人");
		invoice.setAttribute("OriginalInvoiceCode", "");
		invoice.setAttribute("OriginalInvoiceNo", "");
		invoice.setAttribute("OriginalInvoiceSerialNo", "");
		invoice.setAttribute("OpenInvoicePerson", "");
		invoice.setAttribute("OpenInvoiceDate", "");
		invoice.setAttribute("ReceptionUnit", "日用品有限公司");
		print.addContent(invoice);

		Element invoiceParts = new Element("InvoiceParts");
		Element invoicePart = new Element("InvoicePart");
		invoicePart.setAttribute("TransType", "1");
		invoicePart.setAttribute("InvoiceCode", "");
		invoicePart.setAttribute("InvoiceEncode", "");
		invoicePart.setAttribute("InvoiceAmount", "1.0");
		invoicePart.setAttribute("CancelPrint", "0");
		invoicePart.setAttribute("PageNo", "1");
		invoiceParts.addContent(invoicePart);

		invoicePart = new Element("InvoicePart");
		invoicePart.setAttribute("TransType", "1");
		invoicePart.setAttribute("InvoiceCode", "");
		invoicePart.setAttribute("InvoiceEncode", "");
		invoicePart.setAttribute("InvoiceAmount", "0");
		invoicePart.setAttribute("CancelPrint", "0");
		invoicePart.setAttribute("PageNo", "2");
		invoiceParts.addContent(invoicePart);
		print.addContent(invoiceParts);

		Element targetMessages = new Element("TargetMessages");
		print.addContent(targetMessages);

		Element loyaltyInfo = new Element("LoyaltyInfo");
		loyaltyInfo.setAttribute("LoyaltyName", "SNG(1241)");
		loyaltyInfo.setAttribute("TransID", "0");
		loyaltyInfo.setAttribute("CardNumber", "19999420165");
		loyaltyInfo.setAttribute("LoyaltyNumber", "19999420165");
		loyaltyInfo.setAttribute("CustomerName", "SNG");
		loyaltyInfo.setAttribute("Status", "0");
		Element account = new Element("Account");
		account.setAttribute("ID", "1000");
		account.setAttribute("OriginalPoint", "810");
		account.setAttribute("Earned", "0");
		account.setAttribute("Balance", "810");
		loyaltyInfo.addContent(account);
		print.addContent(loyaltyInfo);

		ticket.addContent(print);

		edgePOSTask.addContent(ticket);
		Document document = new Document(edgePOSTask);
		Format format = Format.getRawFormat();
		XMLOutputter xmlOutputter = new XMLOutputter(format);
		String xml = xmlOutputter.outputString(document);
		return xml;
	}
}
