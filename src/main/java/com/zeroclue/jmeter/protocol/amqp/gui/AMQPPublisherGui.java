package com.zeroclue.jmeter.protocol.amqp.gui;

import java.awt.Dimension;

import javax.swing.*;

import lombok.Data;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.config.gui.ArgumentsPanel;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jorphan.gui.JLabeledChoice;
import org.apache.jorphan.gui.JLabeledTextArea;
import org.apache.jorphan.gui.JLabeledTextField;

import com.zeroclue.jmeter.protocol.amqp.AMQPPublisher;

/**
 * AMQP Sampler
 *
 * This class is responsible for ensuring that the Sampler data is kept in step
 * with the GUI.
 *
 * The GUI class is not invoked in non-GUI mode, so it should not perform any
 * additional setup that a test would need at run-time
 *
 */
@Data
public class AMQPPublisherGui extends AMQPSamplerGui {

    private static final long serialVersionUID = 1L;

    private JPanel mainPanel;

    /*
    private static final String[] CONFIG_CHOICES = {"File", "Static"};
    private final JLabeledRadio configChoice = new JLabeledRadio("Message Source", CONFIG_CHOICES);
    private final FilePanel messageFile = new FilePanel("Filename", ALL_FILES);
    */
    private JLabeledTextArea message = new JLabeledTextArea("Message Content");
    private JLabeledTextField messageRoutingKey = new JLabeledTextField("Routing Key");
    private JLabeledTextField messageType = new JLabeledTextField("Message Type");
    private JLabeledTextField replyToQueue = new JLabeledTextField("Reply-To Queue");
    private JLabeledTextField correlationId = new JLabeledTextField("Correlation Id");
    private JLabeledTextField contentType = new JLabeledTextField("ContentType");
    private JLabeledTextField messageId = new JLabeledTextField("Message Id");

    /**
     * modbus 推送信息类型选择
     */
    private JLabeledChoice messageModbusType = new JLabeledChoice("Modbus Message Type",
            new String[]{ "read", "write", "data", "online","offline"});
    /**
     * modbus设备id
     * */
    private JLabeledTextField deviceId = new JLabeledTextField("Device Id");

    /**
     * modbus设备数据点ID，（当消息类型为 read write时才需要）
     * */
    private JLabeledTextField dataId = new JLabeledTextField("DataP Point Id");


    private JCheckBox persistent = new JCheckBox("Persistent?", AMQPPublisher.DEFAULT_PERSISTENT);
    private JCheckBox useTx = new JCheckBox("Use Transactions?", AMQPPublisher.DEFAULT_USE_TX);

    private ArgumentsPanel headers = new ArgumentsPanel("Headers");

    public AMQPPublisherGui(){
        init();
    }

    /**
     * {@inheritDoc}
     */
    public String getLabelResource() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getStaticLabel() {
        return "AMQP Publisher";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void configure(TestElement element) {
        super.configure(element);
        if (!(element instanceof AMQPPublisher)) return;
        AMQPPublisher sampler = (AMQPPublisher) element;

        persistent.setSelected(sampler.getPersistent());
        useTx.setSelected(sampler.getUseTx());

        messageRoutingKey.setText(sampler.getMessageRoutingKey());
        messageType.setText(sampler.getMessageType());
        replyToQueue.setText(sampler.getReplyToQueue());
        contentType.setText(sampler.getContentType());
        correlationId.setText(sampler.getCorrelationId());
        messageId.setText(sampler.getMessageId());
        message.setText(sampler.getMessage());
        messageModbusType.setText(sampler.getMessageModbusType());
        deviceId.setText(sampler.getDeviceId());
        dataId.setText(sampler.getDataId());
        configureHeaders(sampler);
    }

    /**
     * {@inheritDoc}
     */
    public TestElement createTestElement() {
        AMQPPublisher sampler = new AMQPPublisher();
        modifyTestElement(sampler);
        return sampler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyTestElement(TestElement te) {
        AMQPPublisher sampler = (AMQPPublisher) te;
        sampler.clear();
        configureTestElement(sampler);

        super.modifyTestElement(sampler);

        sampler.setPersistent(persistent.isSelected());
        sampler.setUseTx(useTx.isSelected());

        sampler.setMessageRoutingKey(messageRoutingKey.getText());
        sampler.setMessage(message.getText());
        sampler.setMessageType(messageType.getText());
        sampler.setReplyToQueue(replyToQueue.getText());
        sampler.setCorrelationId(correlationId.getText());
        sampler.setContentType(contentType.getText());
        sampler.setMessageId(messageId.getText());
        sampler.setHeaders((Arguments) headers.createTestElement());
        sampler.setDeviceId(deviceId.getText());
        sampler.setMessageModbusType(messageModbusType.getText());
        sampler.setDataId(dataId.getText());
    }


    @Override
    protected void setMainPanel(JPanel panel){
        mainPanel = panel;
    }

    /**
     * Helper method to set up the GUI screen
     * 页面元素初始化
     */
    @Override
    protected final void init() {
        super.init();
        persistent.setPreferredSize(new Dimension(100, 25));
        useTx.setPreferredSize(new Dimension(100, 25));
        messageRoutingKey.setPreferredSize(new Dimension(100, 25));
        messageType.setPreferredSize(new Dimension(100, 25));
        replyToQueue.setPreferredSize(new Dimension(100, 25));
        correlationId.setPreferredSize(new Dimension(100, 25));
        contentType.setPreferredSize(new Dimension(100, 25));
        messageId.setPreferredSize(new Dimension(100, 25));
        message.setPreferredSize(new Dimension(400, 150));
        messageModbusType.setPreferredSize(new Dimension(100,25));
        deviceId.setPreferredSize(new Dimension(100,25));
        dataId.setPreferredSize(new Dimension(100,25));

        mainPanel.add(persistent);
        mainPanel.add(useTx);
        mainPanel.add(messageRoutingKey);
        mainPanel.add(messageType);
        mainPanel.add(replyToQueue);
        mainPanel.add(correlationId);
        mainPanel.add(contentType);
        mainPanel.add(messageId);
        mainPanel.add(headers);
        mainPanel.add(message);
        mainPanel.add(deviceId);
        mainPanel.add(messageModbusType);
        mainPanel.add(dataId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearGui() {
        super.clearGui();
        persistent.setSelected(AMQPPublisher.DEFAULT_PERSISTENT);
        useTx.setSelected(AMQPPublisher.DEFAULT_USE_TX);
        messageRoutingKey.setText("");
        messageType.setText("");
        replyToQueue.setText("");
        correlationId.setText("");
        contentType.setText("");
        messageId.setText("");
        headers.clearGui();
        message.setText("");
        messageModbusType.setText("");
        deviceId.setText("");
        dataId.setText("");
    }

    private void configureHeaders(AMQPPublisher sampler)
    {
        Arguments sampleHeaders = sampler.getHeaders();
        if (sampleHeaders != null) {
            headers.configure(sampleHeaders);
        } else {
            headers.clearGui();
        }
    }
}