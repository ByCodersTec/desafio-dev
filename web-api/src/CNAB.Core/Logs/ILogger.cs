using System.Runtime.CompilerServices;

namespace CNAB.Core.Logs
{
    public interface ILogger
    {
        void Error<TContext>(string msg, [CallerMemberName] string memberName = "", [CallerLineNumber] int sourceLineNumber = 0);
        void Info<TContext>(string msg, [CallerMemberName] string memberName = "", [CallerLineNumber] int sourceLineNumber = 0);
        void Warning<TContext>(string msg, [CallerMemberName] string memberName = "", [CallerLineNumber] int sourceLineNumber = 0);
        void Debug<TContext>(string msg, [CallerMemberName] string memberName = "", [CallerLineNumber] int sourceLineNumber = 0);
    }
}
