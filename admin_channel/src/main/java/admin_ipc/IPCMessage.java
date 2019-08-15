package admin_ipc;
import io.mappedbus.MappedBusMessage;
import io.mappedbus.MemoryMappedFile;

public class IPCMessage implements MappedBusMessage {
    private int sessionIdSize;
    private String sessionId;
    private int messageLength;
    private byte[] byteMessage;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
        sessionIdSize = sessionId.length();
    }

    public byte[] getByteMessage() {
        return byteMessage;
    }

    public void setByteMessage(byte[] byteMessage) {
        this.byteMessage = byteMessage;
        messageLength = byteMessage.length;
    }

    @Override
    public void write(MemoryMappedFile memoryMappedFile, long pos) {
        memoryMappedFile.putInt(pos, sessionIdSize);
        memoryMappedFile.putInt(pos + 4, messageLength);
        memoryMappedFile.setBytes(pos + 8, sessionId.getBytes(), 0, sessionIdSize);
        memoryMappedFile.setBytes(pos+ sessionIdSize + 8, byteMessage, 0, messageLength);
    }

    @Override
    public void read(MemoryMappedFile memoryMappedFile, long pos) {
        sessionIdSize = memoryMappedFile.getInt(pos);
        messageLength = memoryMappedFile.getInt(pos + 4);
        byte[] sessionIdBytes = new byte[sessionIdSize];
        memoryMappedFile.getBytes(pos + 8, sessionIdBytes, 0,  sessionIdSize);
        sessionId = new String(sessionIdBytes);
        byte[] messageBytes = new byte[messageLength];
        memoryMappedFile.getBytes(pos + sessionIdSize + 8, messageBytes, 0, messageLength);
        byteMessage = messageBytes;
    }

    @Override
    public int type() {
        return 0;
    }
}
